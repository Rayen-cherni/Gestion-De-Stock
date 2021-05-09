package com.example.gestiondestock.controller;

import com.example.gestiondestock.controller.api.ClientApi;
import com.example.gestiondestock.dto.ClientDto;
import com.example.gestiondestock.services.ClientService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientController implements ClientApi {

    private ClientService clientService;
    @Override
    public ClientDto save(ClientDto dto) {
        return clientService.save(dto);
    }

    @Override
    public ClientDto findById(Integer id) {
        return clientService.findById(id);
    }

    @Override
    public List<ClientDto> findAll() {
        return clientService.findAll();
    }

    @Override
    public void delete(Integer id) {
    clientService.delete(id);
    }
}
