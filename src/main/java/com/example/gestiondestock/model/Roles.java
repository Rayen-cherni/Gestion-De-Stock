package com.example.gestiondestock.model;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "roles")
//Pour chaque user on a un seul role
public class Roles extends AbstractEntity{

    //****** Les regles de spring dans la classe des roles: *****
        // il faut creer un champ ID (dans notre cas le champ ID a ete creer dans l'abstarctEntity)
        // il faut creer un champ roleName (il faut l'appeller comme ca pour spring le connaitre :) )

    @Column(name = "rolename")
    private String roleName;

    @ManyToOne
    @JoinColumn(name = "idutilisateur")
    private Utilisateur utilisateur;
}
