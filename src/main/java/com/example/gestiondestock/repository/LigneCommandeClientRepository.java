package com.example.gestiondestock.repository;

import com.example.gestiondestock.model.LigneCommandeClient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LigneCommandeClientRepository extends JpaRepository<LigneCommandeClient,Integer> {

    List<LigneCommandeClient> findAllByCommandeClientId(Integer id);
}
