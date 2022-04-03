package com.example.appel.dao;

import com.example.appel.config.HibernateUtil;
import com.example.appel.model.Etat;
import com.example.appel.model.Etudiant;
import com.example.appel.model.FicheAppel;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class EtatDAO extends DAO<Etat> {

    private Class<Etat> etat;
    public EtatDAO(){
        this.etat=Etat.class;
    }
    public void update(Etat etat){
        try(Session session= HibernateUtil.getSessionFactory().getCurrentSession()){
            Transaction t=session.beginTransaction();
            session.update(etat);
            t.commit();
        }
    }

    public void delete(Etat etat){
        try(Session session= HibernateUtil.getSessionFactory().getCurrentSession()){
            Transaction t=session.beginTransaction();
            session.delete(etat);
            t.commit();
        }
    }

    public Etat find(Integer etudiantId, Integer ficheAppelId){
        Etat et=null;
        try(Session session= HibernateUtil.getSessionFactory().getCurrentSession()){
            Transaction t=session.beginTransaction();
            String hql="select e " +
                    "from Etat e " +
                    "where e.etudiant.id= :etudiantId " +
                    "and e.ficheAppel.id= :ficheAppelId";
            Query<Etat> query= session.createQuery(hql);
            query.setParameter("etudiantId",etudiantId);
            query.setParameter("ficheAppelId",ficheAppelId);
            if (!query.getResultList().isEmpty()) {
                et = query.uniqueResult();
            }
            return et;
        }
    }

    public List<Etat> getEtats(Integer ficheAppelId){
        List<Etat> etats=new ArrayList<>();
        try(Session session= HibernateUtil.getSessionFactory().getCurrentSession()){
            Transaction t=session.beginTransaction();
            String hql="select e " +
                    "from Etat e " +
                    "where e.ficheAppel.id= :ficheAppelId";
            etats=session.createQuery(hql).setParameter("ficheAppelId",ficheAppelId).list();
        }
        return etats;
    }

}
