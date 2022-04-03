package com.example.appel.model;

import com.example.appel.enumType.Role;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn
public class Enseignant extends Utilisateur implements Serializable {

    @OneToMany(mappedBy = "enseignant",fetch = FetchType.EAGER)
    private List<Seance> seances=new ArrayList<>();

    public Enseignant() {}

    public Enseignant(String nom, String prenom, String email,String phone, String password, Role role) {
        super(nom, prenom, email, phone, password, role);
    }

    public List<Seance> getSeances() {
        return seances;
    }

    public void setSeances(List<Seance> seances) {
        this.seances = seances;
    }
}
