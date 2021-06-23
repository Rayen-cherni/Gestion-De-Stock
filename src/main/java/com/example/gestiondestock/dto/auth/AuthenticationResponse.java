package com.example.gestiondestock.dto.auth;

import com.example.gestiondestock.dto.UtilisateurDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationResponse {

    private String accessToken;
    private UtilisateurDto utilisateurDto;
}
