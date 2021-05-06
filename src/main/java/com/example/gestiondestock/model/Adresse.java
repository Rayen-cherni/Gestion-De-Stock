package com.example.gestiondestock.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
//@Builder // Permet de creer un Builder basé sur le design patern Builder, le design partn builder est une classe permet de creer un objet
         // Exposant des methodes ayant la meme noms des attribus de la classe mere
        //  La methode Builder va creer l'insatnce de la classe
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable //cad que ce champ est composé et sera utilisé par des autres entité
public class Adresse implements Serializable {

    @Column(name = "adresse1")
    private String adresse1;

    @Column(name = "adresse2")
    private String adresse2;

    @Column(name = "ville")
    private String ville;

    @Column(name = "codepostale")
    private String codePostale;

    @Column(name = "pays")
    private String pays;

}
