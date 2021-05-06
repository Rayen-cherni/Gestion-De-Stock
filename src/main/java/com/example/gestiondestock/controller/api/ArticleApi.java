package com.example.gestiondestock.controller.api;

import com.example.gestiondestock.dto.ArticleDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import static com.example.gestiondestock.utils.constants.APP_ROOT;


//pour dire a swagger c'est une API
@Api(APP_ROOT + "/articles")
public interface ArticleApi {

    @PostMapping(value = APP_ROOT + "/articles/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    //Pour faire la documentation de votre API
    @ApiOperation(value = "Enregister un artcile",notes = "Permet d'enregister ou bien modifier un article",response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet article cree/ modifie"),
            @ApiResponse(code=400, message = "L'objet article n'est pas valide")
    })
    ArticleDto save(@RequestBody ArticleDto dto);


    @GetMapping(value = APP_ROOT+"/articles/{idArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechecher d'un artcile",notes = "Permet de rechercher un article par son ID",response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet article a ete trouvé dans le DB"),
            @ApiResponse(code = 404, message = "Aucun article n'existe dans la DB avec l'ID fourni ")
    })
    ArticleDto findById(@PathVariable("idArticle") Integer id);


    @GetMapping(value = APP_ROOT+"/articles/{codeArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechecher d'un artcile",notes = "Permet de rechercher un article par son CODE",response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet article a ete trouvé dans le CODE"),
            @ApiResponse(code = 404, message = "Aucun article n'existe dans la DB avec l'CODE fourni ")
    })
    ArticleDto findByCodeArticle(@PathVariable("codeArticle") String codeArticle);

    @GetMapping(value = APP_ROOT+"/articles/all",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi tous les articles",notes = "Permet de retouner tous les articles qui extes dans la DB",
            responseContainer = "List<ArticleDto >")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "la liste des articles ou bien une liste vide"),
    })
    List<ArticleDto> findAll();

    @DeleteMapping(value = APP_ROOT+"/articles/delete/{idArticle}")
    @ApiOperation(value = "Supprimer un artcile",notes = "Permet de supprimer un article par son ID",response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "l'artcile a ete supprimé "),
    })
    void delete(@PathVariable("idArticle")Integer id);

}
