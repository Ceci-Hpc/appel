package com.example.appel.model;

import org.apache.ibatis.ognl.EnumerationElementsAccessor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class FicheAppel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    private Seance seance;

    @ManyToOne
    private Scolarite scolarite;

    @OneToMany(mappedBy = "ficheAppel")
    private List<Etat> etats=new ArrayList<>();

    @Column(nullable = false)
    private boolean valide=false;

    @Column(nullable = false)
    private boolean enregistre=false;

    public FicheAppel() {}

    public FicheAppel(Seance seance) {
        this.seance = seance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Seance getSeance() {
        return seance;
    }

    public void setSeance(Seance seance) {
        this.seance = seance;
    }

    public Scolarite getScolarite() {
        return scolarite;
    }

    public void setScolarite(Scolarite scolarite) {
        this.scolarite = scolarite;
    }

    public List<Etat> getEtats() {
        return etats;
    }

    public void setEtats(List<Etat> etats) {
        this.etats = etats;
    }

    public boolean isValide() {
        return valide;
    }

    public void setValide(boolean valide) {
        this.valide = valide;
    }

    public boolean isEnregistre() {
        return enregistre;
    }

    public void setEnregistre(boolean enregistre) {
        this.enregistre = enregistre;
    }


}
