package com.example.gestiondestock.services.auth;

import com.example.gestiondestock.dto.UtilisateurDto;
import com.example.gestiondestock.model.auth.ExtendedUser;
import com.example.gestiondestock.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {

    @Autowired
    private UtilisateurService utilisateurService;

    //Load les cordonnees d'un user par son username
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UtilisateurDto user = utilisateurService.findByEmail(email);

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleName())));

        return new ExtendedUser(user.getEmail(), user.getMotDePasse(), user.getEntreprise().getId(), authorities);

        //Collections.emptyList() va recoit les roles d'un user
        //return new User(user.getEmail(),user.getMotDePasse(), Collections.emptyList()); //Importer cette class from spring.security
        //return new User("rayen.cherni1160@gmail.com","rayen.cherni1160@gmail.com",Collections.emptyList());
    }
}
