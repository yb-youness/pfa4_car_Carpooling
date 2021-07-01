package com.pfa.backend.models;

import javax.persistence.*;

@Entity
@Table(name = "covoiturage")
public class Covoiturage {

   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nbr_places", nullable = false, length = 20)
    private int nbr_places;


    // relation avec trajet
    @ManyToOne
    @JoinColumn(name = "trajet_id", referencedColumnName = "id",nullable = false)
    private Trajet trajet;


    // relation avec User
    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id",nullable = false)
    private User users;


    //getters && setters

    public Trajet getTrajet() {
        return trajet;
    }

    public User getUser() {
        return users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNbr_places() {
        return nbr_places;
    }

    public void setNbr_places(int nbr_places) {
        this.nbr_places = nbr_places;
    }
}
