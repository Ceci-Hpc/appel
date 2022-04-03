package com.example.appel.dao;

import com.example.appel.config.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;


public abstract class DAO<T> {

    private Class<T> entity;

    public void create(T entity) {
        Transaction transaction = null;
        Integer id = null;
        try(Session session= HibernateUtil.getSessionFactory().getCurrentSession()){
            Transaction t=session.beginTransaction();
            session.save(entity);
            t.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

}
