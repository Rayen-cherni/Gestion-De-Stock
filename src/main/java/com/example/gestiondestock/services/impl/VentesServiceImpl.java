package com.example.gestiondestock.services.impl;

import com.example.gestiondestock.dto.LigneVenteDto;
import com.example.gestiondestock.dto.VentesDto;
import com.example.gestiondestock.exception.ErrorCodes;
import com.example.gestiondestock.exception.InvalidEntityException;
import com.example.gestiondestock.exception.EntityNotFoundException;
import com.example.gestiondestock.model.Article;
import com.example.gestiondestock.model.LigneVente;
import com.example.gestiondestock.model.Ventes;
import com.example.gestiondestock.repository.ArticleRepository;
import com.example.gestiondestock.repository.LigneVenteRepository;
import com.example.gestiondestock.repository.VentesRepository;
import com.example.gestiondestock.services.VentesService;
import com.example.gestiondestock.validator.VentesValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class VentesServiceImpl implements VentesService {

    private final ArticleRepository articleRepository;
    private final VentesRepository ventesRepository;
    private final LigneVenteRepository ligneVenteRepository;

    @Autowired
    public VentesServiceImpl(ArticleRepository articleRepository, VentesRepository ventesRepository, LigneVenteRepository ligneVenteRepository) {
        this.articleRepository = articleRepository;
        this.ventesRepository = ventesRepository;
        this.ligneVenteRepository = ligneVenteRepository;
    }

    @Override
    public VentesDto save(VentesDto dto) {
        List<String> errors = VentesValidator.validate(dto);
        if (!errors.isEmpty()){
            log.error("Ventes n'est pas valide");
            throw new InvalidEntityException("L'objet vente n'est pas valide", ErrorCodes.VENTE_NOT_VALID, errors);
        }

        List<String> articlesErrors = new ArrayList<>();

        dto.getLigneVentes().forEach(ligneVenteDto -> {
            Optional<Article> article = articleRepository.findById(ligneVenteDto.getArticle().getId());

            if (article.isEmpty()){
                articlesErrors.add("Aucun article avec l'ID "+ ligneVenteDto.getArticle().getId() );
            }
        });

        if (!articlesErrors.isEmpty()){
            log.error("Ventes n'est pas valide");
            throw new InvalidEntityException("Un ou plusieurs articles n'ont pas ete trouve dans la BDD", ErrorCodes.VENTE_NOT_VALID, errors);
        }
        Ventes savedVentes = ventesRepository.save(VentesDto.toEntity(dto));

        dto.getLigneVentes().forEach(ligneVenteDto -> {
            LigneVente ligneVente = LigneVenteDto.toEntity(ligneVenteDto);
            ligneVente.setVente(savedVentes);
            ligneVenteRepository.save(ligneVente);
        });
        return VentesDto.fromEntity(savedVentes);
    }

    @Override
    public VentesDto findById(Integer id) {
        if(id == null){
            System.out.println("Vente ID is null");
            return null;
        }

        return ventesRepository.findById(id).map(VentesDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("Aucun vente n'a ete trouve dans la BDD", ErrorCodes.VENTE_NOT_FOUND));
    }

    @Override
    public VentesDto findByCode(String code) {
        if(code == null){
            System.out.println("Vente CODE is null");
            return null;
        }
        return ventesRepository.findVentesByCode(code).map(VentesDto::fromEntity)
                .orElseThrow(()->new EntityNotFoundException(
                        "Aucune vente client n'a ete trouve avec le CODE " + code, ErrorCodes.VENTE_NOT_VALID
                ));
    }

    @Override
    public List<VentesDto> findAll() {
        return ventesRepository.findAll().stream()
                .map(VentesDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if(id == null){
            System.out.println("Vente CODE is null");
            return;
        }
        ventesRepository.deleteById(id);
    }
}
