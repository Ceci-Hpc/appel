package com.example.appel.dao;

import com.example.appel.config.HibernateUtil;
import com.example.appel.model.Etudiant;
import com.example.appel.model.Scolarite;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class ScolariteDAO extends DAO<Scolarite> {
    private Class<Scolarite> scolarite;
    public ScolariteDAO(){
        this.scolarite=Scolarite.class;
    }

    public static List<Etudiant> getListEtu(){
        List<Etudiant> etudiants = new ArrayList<>();
        try(Session session= HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            String hql= "select e from Etudiant e "
                    ;
            etudiants= session.createQuery(hql).list();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return etudiants;
    }

}
