package com.example.gestiondestock.repository;

import com.example.gestiondestock.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CategoryRepository extends JpaRepository<Category,Integer> {
    Optional<Category> findCategoryByCode(String code);
}
