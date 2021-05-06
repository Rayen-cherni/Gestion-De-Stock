package com.example.gestiondestock.model;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)

@Entity
@Table(name = "commandefournisseur") //c'est optionelle car si on le precise pas hebernate va prendre le nom de classe comme nom de table par default dans la DB

public class CommandeFournisseur extends AbstractEntity{

    @Column(name = "code")
    private String code;
    @Column(name = "datecommadne")
    private Instant dateCommande;

    @ManyToOne
    @JoinColumn(name = "idfournisseur")
    private Fournisseur fournisseur;

    @OneToMany(mappedBy = "commandefournisseur")
    private List<LigneCommandeFournisseur> ligneCommandeFournisseurs;

    @Column(name = "identreprise")
    private Integer idEntreprise;


    @Column(name = "etatcommande")
    private EtatCommande etatCommande;

}
