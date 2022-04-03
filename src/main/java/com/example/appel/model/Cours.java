package com.example.appel.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cours {
    //--------attribut---------//
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nomCours;

    @Column(nullable = false)
    private int nbrTotal;

    //----------relation------------//
    @ManyToOne
    private Formation formation;

    @OneToMany(mappedBy = "cours",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Seance> seances=new ArrayList<>();

    //-----------constructeur---------------//
    public Cours() {}

    public Cours(String nomCours, int nbrTotal,Formation formation) {
        this.nomCours = nomCours;
        this.nbrTotal = nbrTotal;
        this.formation = formation;
    }


    //--------------Methode----------//
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomCours() {
        return nomCours;
    }

    public void setNomCours(String nomCours) {
        this.nomCours = nomCours;
    }

    public int getNbrTotal() {
        return nbrTotal;
    }

    public void setNbrTotal(int nbrTotal) {
        this.nbrTotal = nbrTotal;
    }

    public Formation getFormation() {
        return formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    public List<Seance> getSeances() {
        return seances;
    }

    public void setSeances(List<Seance> seances) {
        this.seances = seances;
    }

    @Override
    public String toString() {
        return "Cours{" +
                "id=" + id +
                ", nomCours='" + nomCours + '\'' +
                ", nbrTotal=" + nbrTotal +
                '}';
    }
}
