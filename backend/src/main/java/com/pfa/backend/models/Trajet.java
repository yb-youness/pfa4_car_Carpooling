package com.pfa.backend.models;


import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "trajet")
public class Trajet {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = " Le Champ Ville De Départ Obligatoire !! ")
    @Column(name ="villedepart" ,nullable = false,  length = 45)
    private String villedepart;

    @NotBlank(message = "Le Champ Ville D arrivée Obligatoire !!")
    @Column(name ="Villarrive",nullable = false, length = 64)
    private String Villarrive;


    private Timestamp datedepart;


    private Timestamp date_arrivee;

    //@NotBlank annotation must be applied on any String field only.
    // To validate Integer type field, use annotation @NotNull.

    @Column(name = "nbr_places", nullable = false, length = 20)
    @Range(min=0 ,max =4 ,message = "Veuillez Saisir Un Nombre De Place Entre 1 et 4  !!")
    private int nbr_places;


    @Range(min=10 ,message = "Veuillez Saisir Un Cout Superieur Ou Égal à 10  !!")
    @Column(name = "cout", nullable = false, length = 20)
    private int cout;

    @NotBlank(message = " Le Champ Type de Trajet Obligatoire !!")
    @Column(name = "TypeTrajet" ,nullable = false, length = 20)
    private String TypeTrajet;

    // 0 means is in the waiting list
    // 1 == active
    // 2 == refuser
    @Column(name = "status", nullable = true)
    private int status;

    //relation avec user
    @ManyToOne
    @JoinColumn(name="id_user", nullable=false)
    private User users;

    //relation avec covoiturage
    @OneToMany(mappedBy = "trajet")
    private Set<Covoiturage> covoiturages;

    public User getUser() {
        return users;
    }

    public void setUser(User user) {
        this.users = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVilledepart() {
        return villedepart;
    }

    public void setVilledepart(String villedepart) {
        this.villedepart = villedepart;
    }

    public String getVillarrive() {
        return Villarrive;
    }

    public void setVillarrive(String villarrive) {
        Villarrive = villarrive;
    }

    public Timestamp getDatedepart() {
        return datedepart;
    }

    public void setDatedepart(Timestamp datedepart) {
        this.datedepart = datedepart;
    }

    public Timestamp getDate_arrivee() {
        return date_arrivee;
    }

    public void setDate_arrivee(Timestamp date_arrivee) {
        this.date_arrivee = date_arrivee;
    }

    public int getNbr_places() {
        return nbr_places;
    }

    public void setNbr_places(int nbr_places) {
        this.nbr_places = nbr_places;
    }

    public int getCout() {
        return cout;
    }

    public void setCout(int cout) {
        this.cout = cout;
    }

    public String getTypeTrajet() {
        return TypeTrajet;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setTypeTrajet(String typeTrajet) {
        TypeTrajet = typeTrajet;
    }

    @Override
    public String toString() {
        return "Trajet{" +
                "id=" + id +
                ", villedepart='" + villedepart + '\'' +
                ", Villarrive='" + Villarrive + '\'' +
                ", datedepart=" + datedepart +
                ", date_arrivee=" + date_arrivee +
                ", nbr_places=" + nbr_places +
                ", cout=" + cout +
                ", TypeTrajet='" + TypeTrajet + '\'' +
                ", user=" + users +

                '}';
    }
}

