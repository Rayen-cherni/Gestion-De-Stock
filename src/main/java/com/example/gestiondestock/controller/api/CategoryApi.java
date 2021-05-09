package com.example.gestiondestock.controller.api;

import com.example.gestiondestock.dto.CategoryDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.gestiondestock.utils.constants.APP_ROOT;

@Api(APP_ROOT + "/categories")
public interface CategoryApi {

    @PostMapping(value = APP_ROOT + "/categories/create", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregister une categorie dans la DB", notes = "Cette methode vous permez d'ajouter" +
            "une categorie dans la DB", response = CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet categorie cree/ modifie"),
            @ApiResponse(code = 400, message = "L'objet categorie n'est pas valide")
    })
    CategoryDto save(@RequestBody CategoryDto dto);

    @GetMapping(value = APP_ROOT + "/categories/filter/{idCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechecher d'un categorie", notes = "Permet de rechercher un categorie par son ID",
            response = CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet categorie a ete trouvé dans le DB"),
            @ApiResponse(code = 404, message = "Aucun categorie n'existe dans la DB avec l'ID fourni ")
    })
    CategoryDto findById(@PathVariable("idCategory") Integer id);


    @GetMapping(value = APP_ROOT + "/categories/{codeCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechecher d'un categorie", notes = "Permet de rechercher un categorie par son CODE",
            response = CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet categorie a ete trouvé dans le CODE"),
            @ApiResponse(code = 404, message = "Aucun categorie n'existe dans la DB avec l'CODE fourni ")
    })
    CategoryDto findByCode(@PathVariable("codeCategory") String codeCategory);

    @GetMapping(value = APP_ROOT + "/categories/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi tous les categories", notes = "Permet de retouner tous les categories qui extes dans la DB",
            responseContainer = "List<CategoryDto >")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "la liste des categories ou bien une liste vide"),
    })
    List<CategoryDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/categories/delete/{idCategory}")
    @ApiOperation(value = "Supprimer un artcile", notes = "Permet de supprimer un category par son ID",
            response = CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "l'artcile a ete supprimé "),
    })
    void delete(@PathVariable("idCategory") Integer id);
}
