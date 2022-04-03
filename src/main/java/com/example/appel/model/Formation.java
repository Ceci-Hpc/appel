package com.example.appel.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Formation {
    //-----------attribut-----------//
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nomFormation;

    //-----------relation-------------//
    @OneToMany(mappedBy = "formation")
    private List<Cours> cours = new ArrayList<>();

    @OneToMany(mappedBy = "formation")
    private List<Etudiant> etudiants=new ArrayList<>();

    //------------constructeur-------------//
    public Formation() {}

    public Formation(String nomFormation) {
        this.nomFormation = nomFormation;
    }

    public Formation(int id,String nomFormation){
        this.id=id;
        this.nomFormation=nomFormation;
    }


    //----------------Methode---------------//
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomFormation() {
        return nomFormation;
    }

    public void setNomFormation(String nomFormation) {
        this.nomFormation = nomFormation;
    }

    public List<Cours> getCours() {
        return cours;
    }

    public void setCours(List<Cours> cours) {
        this.cours = cours;
    }

    public List<Etudiant> getEtudiants() {
        return etudiants;
    }

    public void setEtudiants(List<Etudiant> etudiants) {
        this.etudiants = etudiants;
    }

    @Override
    public String toString() {
        return "Formation{" +
                "id=" + id +
                ", nomFormation='" + nomFormation + '\'' +
                '}';
    }
}
