package com.example.appel.model;

import com.example.appel.enumType.GroupEtudiant;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Seance {
    //----------------------attribut-------------------//
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int numSeance;

    @Column(nullable = false)
    private String salle;

    @Column(nullable = false)
    private Date dateDebut;

    @Column(nullable = false)
    private Date dateFin;

    @Enumerated(EnumType.STRING)
    private GroupEtudiant groupeTD;

    //----------relation----------//
    @ManyToOne
    @JoinColumn(nullable = false)
    private Cours cours;

    @OneToOne(mappedBy = "seance")
    private FicheAppel ficheAppel;

    @ManyToOne
    private Enseignant enseignant;

    //--------------constructeur---------------//

    public Seance() {
    }

    public Seance(int numSeance, String salle, Date dateDebut, Date dateFin, Cours cours, Enseignant enseignant, GroupEtudiant groupeTD) {
        this.numSeance = numSeance;
        this.salle = salle;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.cours = cours;
        this.enseignant = enseignant;
        this.groupeTD = groupeTD;
    }

    //----------------Methode-----------------//
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumSeance() {
        return numSeance;
    }

    public void setNumSeance(int numSeance) {
        this.numSeance = numSeance;
    }

    public String getSalle() {
        return salle;
    }

    public void setSalle(String salle) {
        this.salle = salle;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public GroupEtudiant getGroupeTD() {
        return groupeTD;
    }

    public void setGroupeTD(GroupEtudiant groupeTD) {
        this.groupeTD = groupeTD;
    }

    public Cours getCours() {
        return cours;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }

    public FicheAppel getFicheAppel() {
        return ficheAppel;
    }

    public void setFicheAppel(FicheAppel ficheAppel) {
        this.ficheAppel = ficheAppel;
    }

    public Enseignant getEnseignant() {
        return enseignant;
    }

    public void setEnseignant(Enseignant enseignant) {
        this.enseignant = enseignant;
    }

    @Override
    public String toString() {
        return "Seance{" +
                "id=" + id +
                ", numSeance=" + numSeance +
                ", salle='" + salle + '\'' +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", cours=" + cours.getNomCours() +
                ", enseignant=" + enseignant.getNom() +" "+enseignant.getPrenom()+
                '}';
    }
}
