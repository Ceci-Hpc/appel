package com.example.appel.model;

import com.example.appel.enumType.Role;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@PrimaryKeyJoinColumn
public class Scolarite extends Utilisateur implements Serializable {

    @OneToMany(mappedBy = "scolarite",cascade = CascadeType.ALL)
    private List<Justificatif> justificatifs=new ArrayList<>();

    public Scolarite() {}

    public Scolarite(String nom, String prenom, String email,String phone, String password, Role role) {
        super(nom, prenom, email, phone, password, role);
    }

    public List<Justificatif> getJustificatifs() {
        return justificatifs;
    }

    public void setJustificatifs(List<Justificatif> justificatifs) {
        this.justificatifs = justificatifs;
    }
}
