package com.example.gestiondestock.controller;

import com.example.gestiondestock.controller.api.CommandeClientApi;
import com.example.gestiondestock.dto.CommandeClientDto;
import com.example.gestiondestock.repository.CommandeClientRepository;
import com.example.gestiondestock.services.CommandeClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommandeClientController implements CommandeClientApi {

    @Autowired
    private CommandeClientService commandeClientService;

    @Override
    public ResponseEntity<CommandeClientDto> save(CommandeClientDto dto) {
        //return ResponseEntity.ok(commandeClientService.save(dto))
        return ResponseEntity.status(HttpStatus.OK).body(commandeClientService.save(dto));
    }

    @Override
    public ResponseEntity<CommandeClientDto> findById(Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(commandeClientService.findById(id));
    }

    @Override
    public ResponseEntity<CommandeClientDto> findByCode(String codeCommandeClient) {
        return ResponseEntity.status(HttpStatus.OK).body(commandeClientService.findByCode(codeCommandeClient));
    }

    @Override
    public ResponseEntity<List<CommandeClientDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(commandeClientService.findAll());
    }

    @Override
    public ResponseEntity delete(Integer id) {
        commandeClientService.delete(id);
        return ResponseEntity.ok().build();
    }
}
