package com.example.gestiondestock.controller.api;

import com.example.gestiondestock.dto.ClientDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.gestiondestock.utils.constants.APP_ROOT;

@Api(APP_ROOT+"/clients")
public interface ClientApi {

    @PostMapping(value = APP_ROOT + "/clients/create", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregister une categorie dans la DB", notes = "Cette methode vous permez d'ajouter" +
            "une cleint dans la DB", response = ClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet client cree/ modifie"),
            @ApiResponse(code = 400, message = "L'objet client n'est pas valide")
    })
    ClientDto save(@RequestBody ClientDto dto);

    @GetMapping(value = APP_ROOT + "/clients/{idClient}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechecher d'un client", notes = "Permet de rechercher un categorie par son ID",
            response = ClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet categorie a ete trouvé dans le DB"),
            @ApiResponse(code = 404, message = "Aucun categorie n'existe dans la DB avec l'ID fourni ")
    })
    ClientDto findById(@PathVariable("idClient") Integer id);

    @GetMapping(value = APP_ROOT + "/clients/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi tous les clients", notes = "Permet de retouner tous les clients qui existes dans la DB",
            responseContainer = "List<ClientDto >")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "la liste des clients ou bien une liste vide"),
    })
    List<ClientDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/clients/delete/{idClient}")
    @ApiOperation(value = "Supprimer un client", notes = "Permet de supprimer un client par son ID",
            response = ClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "l'client a ete supprimé "),
    })
    void delete(@PathVariable("idClient") Integer id);
}
