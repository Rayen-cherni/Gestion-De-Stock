package com.example.gestiondestock.repository;

import com.example.gestiondestock.model.MvtStk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MvtStkRepository extends JpaRepository<MvtStk,Integer> {
}
