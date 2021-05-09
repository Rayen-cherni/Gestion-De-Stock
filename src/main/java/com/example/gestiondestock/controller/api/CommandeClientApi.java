package com.example.gestiondestock.controller.api;


import com.example.gestiondestock.dto.CommandeClientDto;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import static com.example.gestiondestock.utils.constants.APP_ROOT;

@Api(APP_ROOT+"/commandesclients")
public interface CommandeClientApi {

    @PostMapping(value = APP_ROOT+"/commandesclients/create")
    ResponseEntity<CommandeClientDto> save(CommandeClientDto dto);

    @GetMapping(value = APP_ROOT+"/commandesclients/{idCommandeClient}")
    ResponseEntity<CommandeClientDto> findById(@PathVariable("idCommandeClient") Integer id);

    @GetMapping(value = APP_ROOT+"/commandesclients/{codeCommandeClient}")
    ResponseEntity<CommandeClientDto> findByCode(@PathVariable String codeCommandeClient);

    @GetMapping(value = APP_ROOT+"/commandesclients/all")
    ResponseEntity<List<CommandeClientDto>> findAll();

    @DeleteMapping(value = APP_ROOT+"/commadnesclients/delete/{idCommandeClient}")
    ResponseEntity delete(@PathVariable("idCommandeClient") Integer id);

}
