package com.pfa.backend.models;


import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name="users")
public class User {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    @NotBlank(message="Le Nom Et Obligatoir")
    private String nom;
    @NotBlank(message="Le Prenom Et Obligatoir")
    private String prenom;

    @Pattern(regexp = "^(?:(?:\\+|00)33[\\s.-]{0,3}(?:\\(0\\)[\\s.-]{0,3})?|0)[1-9](?:(?:[\\s.-]?\\d{2}){4}|\\d{2}(?:[\\s.-]?\\d{3}){2})$" ,message = "Entrez Un Numéro De Tel De Format Valid !!")
    private String tel;
    @NotBlank(message="L email  Et Obligatoir !! ")
    @Email(message = "Entre Un Email Valide !!")
    @Column(unique = true)

    private String email;
    @NotBlank(message="Le Mot De Pass Et Obligatoir")
    @Size(min=8 ,message = "Entre Un Mot De Pass  a 8 Chiffres !!")
    private String pass;
    private boolean state;  //Active / noActive
    private String roole;  //=> Use Enum


    @NotBlank(message="Le champ Et Obligatoir")
    private String gender;
    // relation avec trajet
    @OneToMany(mappedBy="users")
    private Set<Trajet> trajets;

    // relation avec covoiturage
    @OneToMany(mappedBy = "users")
    private Set<Covoiturage> covoiturages;


//    @Transient
//    @NotBlank(message="Le Mot De Pass Et Obligatoir")
//    @Size(min=8 ,message = "Entre Un Mot De Pass  a 8 Chiffres !!")
//    private String confPass;
    // For Validating Acount Using Tocken Add Attribute  token to this class 

    @PrePersist
    protected void onCreate(){
         this.roole ="ROLE_USER";
         this.state = true;
    }


    public User(){

    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getRoole() {
        return roole;
    }

    public void setRoole(String roole) {
        this.roole = roole;
    }

//    public String getConfPass() {
//        return confPass;
//    }
//
//    public void setConfPass(String confPass) {
//        this.confPass = confPass;
//    }

    @Override
    public String toString() {
        return "User [email=" + email + ", id=" + id + ", nom=" + nom + ", pass=" + pass + ", prenom=" + prenom
                + ", roole=" + roole + ", state=" + state + ", tel=" + tel + "]";
    }
    
    
}
