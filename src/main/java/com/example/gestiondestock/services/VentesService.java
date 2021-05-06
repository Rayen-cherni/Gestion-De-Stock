package com.example.gestiondestock.services;

import com.example.gestiondestock.dto.ArticleDto;
import com.example.gestiondestock.dto.VentesDto;

import java.util.List;

public interface VentesService {

    VentesDto save(VentesDto dto);
    VentesDto findById(Integer id);
    VentesDto findByCode(String code);
    List<VentesDto> findAll();
    void delete(Integer id);
}
