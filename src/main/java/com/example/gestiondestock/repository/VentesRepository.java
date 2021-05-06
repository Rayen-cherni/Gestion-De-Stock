package com.example.gestiondestock.repository;

import com.example.gestiondestock.model.Ventes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VentesRepository extends JpaRepository<Ventes,Integer> {
    Optional<Ventes> findVentesByCode(String code);
}
