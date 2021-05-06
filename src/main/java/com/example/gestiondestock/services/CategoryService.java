package com.example.gestiondestock.services;

import com.example.gestiondestock.dto.CategoryDto;
import com.example.gestiondestock.dto.UtilisateurDto;

import java.util.List;

public interface CategoryService {

    CategoryDto save(CategoryDto dto);

    CategoryDto findById(Integer id);

    CategoryDto findByCode(String code);

    List<CategoryDto> findAll();

    void delete(Integer id);

}
