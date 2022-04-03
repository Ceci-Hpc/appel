package com.example.appel.dao;

import com.example.appel.config.HibernateUtil;
import com.example.appel.model.Formation;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class FormationDAO extends DAO<Formation>{
    private Class<Formation> formation;

    public FormationDAO(){
        this.formation=Formation.class;
    }

    public Formation find(Integer id){
        try(Session session= HibernateUtil.getSessionFactory().getCurrentSession()){
            Transaction t=session.beginTransaction();
            return session.get(formation, id);
        }
    }

    public Formation findByName(String nomFormation){
        Formation f=null;
        try(Session session= HibernateUtil.getSessionFactory().getCurrentSession()){
            Transaction t=session.beginTransaction();
            String hql="select f "+
                    "from Formation f "+
                    "where f.nomFormation= :nomFormation";
            Query<Formation> query= session.createQuery(hql);
            query.setParameter("nomFormation",nomFormation);
            if (!query.getResultList().isEmpty()) {
                f = query.uniqueResult();
            }
            return f;
        }
    }
}
