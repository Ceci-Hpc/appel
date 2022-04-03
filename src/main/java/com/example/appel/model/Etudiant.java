package com.example.appel.model;

import com.example.appel.enumType.GroupEtudiant;
import com.example.appel.enumType.Role;
import com.example.appel.enumType.TypeEtudiant;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn
public class Etudiant extends Utilisateur implements Serializable {

    @Enumerated(EnumType.STRING)
    private TypeEtudiant typeEtudiant;

    @Enumerated(EnumType.STRING)
    private GroupEtudiant groupEtudiant;

    @ManyToOne
    private Formation formation;

    @OneToMany(mappedBy = "etudiant")
    private List<Justificatif> justificatifs=new ArrayList<>();

    @OneToMany(mappedBy = "etudiant")
    private List<Etat> etats=new ArrayList<>();

    @Column
    private int nbAbsence;

    @Column
    private int nbAbsenceJus;

    @Column
    private int nbAbsenceNonJus;

    @Column
    private int nbPresence;

    public Etudiant() {}

    public Etudiant(String prenom, String nom, String email, String phone, String password, Role role, TypeEtudiant typeEtudiant, GroupEtudiant groupEtudiant, Formation formation) {
        super(nom, prenom, email,phone, password, role);
        this.typeEtudiant = typeEtudiant;
        this.groupEtudiant = groupEtudiant;
        this.formation = formation;
    }

    public Etudiant(String prenom, String nom, String email, String phone, String password, Role role, TypeEtudiant typeEtudiant, GroupEtudiant groupEtudiant, Formation formation, int nbAbsence, int nbAbsenceJus, int nbAbsenceNonJus, int nbPresence) {
        super(nom, prenom, email,phone, password, role);
        this.typeEtudiant = typeEtudiant;
        this.groupEtudiant = groupEtudiant;
        this.formation = formation;
        this.nbAbsence = nbAbsence;
        this.nbAbsenceJus = nbAbsenceJus;
        this.nbAbsenceNonJus = nbAbsenceNonJus;
        this.nbPresence = nbPresence;
    }

    public Etudiant(String nom, String prenom, String email, String password, String phone, String photo, Role role, TypeEtudiant typeEtudiant, GroupEtudiant groupEtudiant, Formation formation) {
        super(nom, prenom, email, password, phone, photo, role);
        this.typeEtudiant = typeEtudiant;
        this.groupEtudiant = groupEtudiant;
        this.formation = formation;
    }

    public TypeEtudiant getTypeEtudiant() {
        return typeEtudiant;
    }

    public void setTypeEtudiant(TypeEtudiant typeEtudiant) {
        this.typeEtudiant = typeEtudiant;
    }

    public GroupEtudiant getGroupEtudiant() {
        return groupEtudiant;
    }

    public void setGroupEtudiant(GroupEtudiant groupEtudiant) {
        this.groupEtudiant = groupEtudiant;
    }

    public Formation getFormation() {
        return formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    public List<Justificatif> getJustificatifs() {
        return justificatifs;
    }

    public void setJustificatifs(List<Justificatif> justificatifs) {
        this.justificatifs = justificatifs;
    }

    public List<Etat> getEtats() {
        return etats;
    }

    public void setEtats(List<Etat> etats) {
        this.etats = etats;
    }

    public int getNbAbsence() {
        return nbAbsence;
    }

    public void setNbAbsence(int nbAbsence) {
        this.nbAbsence = nbAbsence;
    }

    public int getNbAbsenceJus() {
        return nbAbsenceJus;
    }

    public void setNbAbsenceJus(int nbAbsenceJus) {
        this.nbAbsenceJus = nbAbsenceJus;
    }

    public int getNbAbsenceNonJus() {
        return nbAbsenceNonJus;
    }

    public void setNbAbsenceNonJus(int nbAbsenceNonJus) {
        this.nbAbsenceNonJus = nbAbsenceNonJus;
    }

    public int getNbPresence() {
        return nbPresence;
    }

    public void setNbPresence(int nbPresence) {
        this.nbPresence = nbPresence;
    }
}
