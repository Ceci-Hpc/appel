package com.example.appel.dao;

import com.example.appel.config.HibernateUtil;
import com.example.appel.model.Enseignant;
import com.example.appel.model.Seance;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class EnseignantDAO extends DAO<Enseignant>{

    private Class<Enseignant> enseignant;
    public EnseignantDAO(){
        this.enseignant=Enseignant.class;
    }

    public Enseignant find(Integer id){
        try(Session session= HibernateUtil.getSessionFactory().getCurrentSession()){
            Transaction t=session.beginTransaction();
            return session.get(enseignant, id);
        }
    }

    public List<Seance> getSeances(Integer enseignantId){
        List<Seance> seances=new ArrayList<>();
        try(Session session= HibernateUtil.getSessionFactory().getCurrentSession()){
            Transaction t=session.beginTransaction();
            Enseignant e=session.get(enseignant,enseignantId);
            seances=e.getSeances();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return seances;
    }
}
