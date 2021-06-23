package com.example.gestiondestock.controller;

import com.example.gestiondestock.dto.UtilisateurDto;
import com.example.gestiondestock.dto.auth.AuthenticationRequest;
import com.example.gestiondestock.dto.auth.AuthenticationResponse;
import com.example.gestiondestock.model.auth.ExtendedUser;
import com.example.gestiondestock.services.UtilisateurService;
import com.example.gestiondestock.services.auth.ApplicationUserDetailsService;
import com.example.gestiondestock.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.gestiondestock.utils.constants.AUTHENTICATION_ENDPOINT;

@RestController
@RequestMapping(AUTHENTICATION_ENDPOINT)
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private ApplicationUserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;

    //Extra features
    @Autowired
    private UtilisateurService utilisateurService;


    //Le responsable sur la retour de la response lors de l'authentication
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getLogin(),
                        request.getPassword()
                )
        );

        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getLogin());
        final UtilisateurDto utilisateurDto = utilisateurService.findByEmail(request.getLogin());

        final String jwt = jwtUtil.generateToken((ExtendedUser) userDetails);

        return ResponseEntity.ok(
                AuthenticationResponse
                        .builder()
                        .accessToken(jwt)
                        .utilisateurDto(utilisateurDto)
                        .build()
        );
    }
}
