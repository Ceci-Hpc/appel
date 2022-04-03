package com.example.appel.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Justificatif {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = true)
    private String titre;

    @CreationTimestamp
    @Column(nullable = true)
    private Date date;

    @Column(nullable = false)
    private boolean validee = false;

//    @Column(nullable = false)
//    private boolean rejetee = false;

    @Column(nullable = false)
    private boolean traite = false;

    @Column(nullable = true)
    private String document;

    @ManyToOne
    private Scolarite scolarite;

    @ManyToOne
    private Etudiant etudiant;

    @OneToOne
    private Seance seance;

    public Justificatif() {
    }

    public Justificatif(String titre, Date date, boolean validee, boolean traite, Etudiant etudiant, Seance seance,String document) {
        this.titre = titre;
        this.date = date;
        this.validee = validee;
        this.traite = traite;
        this.etudiant = etudiant;
        this.seance=seance;
        this.document=document;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isValidee() {
        return validee;
    }

    public void setValidee(boolean validee) {
        this.validee = validee;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public Scolarite getScolarite() {
        return scolarite;
    }

    public void setScolarite(Scolarite scolarite) {
        this.scolarite = scolarite;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public boolean isTraite() {
        return traite;
    }

    public void setTraite(boolean traite) {
        this.traite = traite;
    }

    public Seance getSeance() {
        return seance;
    }

    public void setSeance(Seance seance) {
        this.seance = seance;
    }
}
