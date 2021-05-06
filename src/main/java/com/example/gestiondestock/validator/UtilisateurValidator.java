package com.example.gestiondestock.validator;

import com.example.gestiondestock.dto.UtilisateurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class UtilisateurValidator {

    public static List<String> validate(UtilisateurDto utilisateurDto) {
        List<String> errors = new ArrayList<>();
        if (utilisateurDto == null) {
            errors.add("Veuillez renseigner le nom d'utilisateur");
            errors.add("Veuillez renseigner le prenom d'utilisateur");
            errors.add("Veuillez renseigner le mot de passe d'utilisateur");
            errors.add("Veuillez renseigner l'adresse d'utilisateur");
            errors.add("Veuillez renseigner l'adresse 1 d'utilisateur");
            errors.add("Veuillez renseigner la ville d'utilisateur");
            return errors;

        }
        if (!StringUtils.hasLength(utilisateurDto.getNom())) {
            errors.add("Veuillez renseigner le nom d'utilisateur");
        }
        if (!StringUtils.hasLength(utilisateurDto.getPrenom())) {
            errors.add("Veuillez renseigner le prenom d'utilisateur");
        }
        if (utilisateurDto.getDateDeNaissance()==null) {
            errors.add("Veuillez renseigner la date de naissance d'util isateur");
        }
        if (!StringUtils.hasLength(utilisateurDto.getMotDePasse())) {
            errors.add("Veuillez renseigner le mot de passe d'utilisateur");
        }
        if (utilisateurDto.getAdresse() == null) {
            errors.add("Veuillez renseigner l'adresse d'utilisateur");
        } else {
            if (!StringUtils.hasLength(utilisateurDto.getAdresse().getAdresse1())) {
                errors.add("Veuillez renseigner l'adresse 1 d'utilisateur");
            }
            if (!StringUtils.hasLength(utilisateurDto.getAdresse().getCodePostale())) {
                errors.add("Veuillez renseigner le code postale d'utilisateur");
            }
            if (!StringUtils.hasLength(utilisateurDto.getAdresse().getVille())) {
                errors.add("Veuillez renseigner la ville d'utilisateur");
            }

            if (!StringUtils.hasLength(utilisateurDto.getAdresse().getPays())) {
                errors.add("Veuillez renseigner le pays d'utilisateur");
            }

        }

        return errors;
    }
}
