package com.example.appel.dao;

import com.example.appel.config.HibernateUtil;
import com.example.appel.model.FicheAppel;
import com.example.appel.model.Formation;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class FicheAppelDAO extends DAO<FicheAppel> {

    private Class<FicheAppel> ficheAppel;
    public FicheAppelDAO(){
        this.ficheAppel=FicheAppel.class;
    }
    public FicheAppel find(Integer id){
        try(Session session= HibernateUtil.getSessionFactory().getCurrentSession()){
            Transaction t=session.beginTransaction();
            return session.get(ficheAppel, id);
        }
    }

    public void update(FicheAppel ficheAppel){
        try(Session session= HibernateUtil.getSessionFactory().getCurrentSession()){
            Transaction t=session.beginTransaction();
            session.update(ficheAppel);
            t.commit();
        }
    }

    public FicheAppel getFicheAppel(Integer seanceId){
        FicheAppel f=null;
        try(Session session= HibernateUtil.getSessionFactory().getCurrentSession()){
            Transaction t=session.beginTransaction();
            String hql="select f "+
                    "from FicheAppel f "+
                    "where f.seance.id= :seanceId";
            Query<FicheAppel> query= session.createQuery(hql);
            query.setParameter("seanceId",seanceId);
            if (!query.getResultList().isEmpty()) {
                f = query.uniqueResult();
            }
            return f;
        }
    }
}
