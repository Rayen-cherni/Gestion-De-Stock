package com.example.gestiondestock.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

//This class be contain all the commun attributes in the application

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class) //Va mettre a jour les champ des @cratedDate && @LastModifiedDate dans la DB
public class AbstractEntity implements Serializable {

    @Id //Specifies the primary key of an entity.
    @GeneratedValue
    private Integer id;

    @CreatedDate //represente la date de creation de l'entité
    @Column(name = "creationDate")
    private Instant creationDate;

    //Instant equi Date
    @LastModifiedDate
    @Column(name="lastModifiedDate")
    //@JsonIgnore //whene i dont need to see this attribute in my documetation i use @jsonignore
    private Instant lastModifiedDate;

    @PrePersist
    void prePersist(){
        creationDate = Instant.now();
    }
    @PreUpdate
    void preUpdate(){
        lastModifiedDate = Instant.now();
    }

}
