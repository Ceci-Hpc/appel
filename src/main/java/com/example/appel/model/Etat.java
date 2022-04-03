package com.example.appel.model;

import com.example.appel.enumType.EtatAppel;

import javax.persistence.*;

@Entity
public class Etat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Etudiant etudiant;

    @ManyToOne
    private FicheAppel ficheAppel;

    @Enumerated(EnumType.STRING)
    private EtatAppel etatAppel;

    public Etat() {}

    public Etat(Etudiant etudiant, FicheAppel ficheAppel, EtatAppel etatAppel) {
        this.etudiant = etudiant;
        this.ficheAppel = ficheAppel;
        this.etatAppel = etatAppel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public FicheAppel getFicheAppel() {
        return ficheAppel;
    }

    public void setFicheAppel(FicheAppel ficheAppel) {
        this.ficheAppel = ficheAppel;
    }

    public EtatAppel getEtatAppel() {
        return etatAppel;
    }

    public void setEtatAppel(EtatAppel etatAppel) {
        this.etatAppel = etatAppel;
    }
}
