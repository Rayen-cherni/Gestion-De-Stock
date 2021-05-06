package com.example.gestiondestock.services.impl;

import com.example.gestiondestock.dto.CommandeClientDto;
import com.example.gestiondestock.dto.LigneCommandeClientDto;
import com.example.gestiondestock.exception.ErrorCodes;
import com.example.gestiondestock.exception.InvalidEntityException;
import com.example.gestiondestock.model.Article;
import com.example.gestiondestock.model.Client;
import com.example.gestiondestock.model.CommandeClient;
import com.example.gestiondestock.model.LigneCommandeClient;
import com.example.gestiondestock.repository.ArticleRepository;
import com.example.gestiondestock.repository.ClientRepository;
import com.example.gestiondestock.repository.CommandeClientRepository;
import com.example.gestiondestock.repository.LigneCommandeClientRepository;
import com.example.gestiondestock.services.CommandeClientService;
import com.example.gestiondestock.validator.CommandeClientValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.gestiondestock.exception.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommandeClientServiceImpl implements CommandeClientService {

    private final CommandeClientRepository commandeClientRepository;
    private final ClientRepository clientRepository;
    private final ArticleRepository articleRepository;
    private final LigneCommandeClientRepository ligneCommandeClientRepository;

    @Autowired
    public CommandeClientServiceImpl(CommandeClientRepository commandeClientRepository, ClientRepository clientRepository, ArticleRepository articleRepository, LigneCommandeClientRepository ligneCommandeClientRepository) {
        this.commandeClientRepository = commandeClientRepository;
        this.clientRepository = clientRepository;
        this.articleRepository = articleRepository;
        this.ligneCommandeClientRepository = ligneCommandeClientRepository;
    }


    @Override
    public CommandeClientDto save(CommandeClientDto dto) {
        List<String> errors = CommandeClientValidator.validate(dto);
        if (!errors.isEmpty()) {
            System.out.println("Commande client n'est pas valide");
            throw new InvalidEntityException("La commande client n'est pas valide", ErrorCodes.COMMANDE_CLIENT_NOT_VALID, errors);
        }
        Optional<Client> client = clientRepository.findById(dto.getClient().getId());

        if (client.isEmpty()) {
            log.warn("Client with id: " + dto.getClient().getId() + "n'est pas trouver dans la DB");
            throw new EntityNotFoundException("Aucun client avec l'ID" + dto.getClient().getId() + " n'a ete trouve dans la BDD",
                    ErrorCodes.CLIENT_NOT_FOUND);
        }
        List<String> articleErrors = new ArrayList<>();

        if (dto.getLigneCommandeClients() != null) {
            dto.getLigneCommandeClients().forEach(ligCmdClt -> {
                        if (ligCmdClt.getArticle() != null) {
                            Optional<Article> article = articleRepository.findById(ligCmdClt.getArticle().getId());
                            if (article.isEmpty()) {
                                articleErrors.add("L'article avec l'ID" + ligCmdClt.getArticle().getId() + "n'existe pas ");
                            }
                        }else{
                            articleErrors.add("Impossible d'ajouter une commande avec un article NULL");
                        }
                    }
            );
        }
        if (!articleErrors.isEmpty()) {
            log.warn("");
            System.out.println("Article n'existe pas dans la BDD");
            throw new InvalidEntityException("Article n'existe pas dans la BDD", ErrorCodes.ARTICLE_NOT_FOUND, articleErrors);
        }

        CommandeClient savedCmdClt = commandeClientRepository.save(CommandeClientDto.toEntity(dto));
        if (dto.getLigneCommandeClients() != null) {
            dto.getLigneCommandeClients().forEach(ligCmdClt -> {
                LigneCommandeClient ligneCommandeClient = LigneCommandeClientDto.toEntity(ligCmdClt);
                ligneCommandeClient.setCommandeClient(savedCmdClt);
                ligneCommandeClientRepository.save(ligneCommandeClient);
            });
        }

        return CommandeClientDto.fromEntity(savedCmdClt);
    }

    @Override
    public CommandeClientDto findById(Integer id) {

        if (id == null){
            log.error("Commande client ID is NULL");
            return null;
        }

        return commandeClientRepository.findById(id).map(CommandeClientDto::fromEntity)
                .orElseThrow(()->
                        new EntityNotFoundException(
                                "Aucune commande client n'a ete trouve avec l'ID " + id, ErrorCodes.COMMANDE_CLIENT_NOT_FOUND
                        ));
    }

    @Override
    public CommandeClientDto findByCode(String code) {
        if (!StringUtils.hasLength(code)){
            log.error("Commande client CODE is NULL");
            return null;
        }

        return commandeClientRepository.findCommandeClientByCode(code).map(CommandeClientDto::fromEntity)
                .orElseThrow(()->
                        new EntityNotFoundException(
                                "Aucune commande client n'a ete trouve avec le CODE " + code, ErrorCodes.COMMANDE_CLIENT_NOT_FOUND
                        ));
    }

    @Override
    public List<CommandeClientDto> findAll() {
        return commandeClientRepository.findAll().stream().map(CommandeClientDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null){
            log.error("Commande client ID is NULL");
            return ;
        }
        commandeClientRepository.deleteById(id);
    }
}
