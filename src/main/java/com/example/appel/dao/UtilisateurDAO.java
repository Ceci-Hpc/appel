package com.example.appel.dao;

import com.example.appel.config.HibernateUtil;
import com.example.appel.model.Utilisateur;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurDAO extends DAO<Utilisateur>{

    private Class<Utilisateur> utilisateur;

    public UtilisateurDAO() {
        this.utilisateur=Utilisateur.class;
    }

    public Utilisateur find(Integer id){
        try(Session session= HibernateUtil.getSessionFactory().getCurrentSession()){
            Transaction t=session.beginTransaction();
            return session.get(utilisateur, id);
        }
    }

    public void update(Utilisateur utilisateur) {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            session.update(utilisateur);
            t.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Integer ConnectUser(String email,String pwd){
        Integer utilisateurId=null;
        try(Session session= HibernateUtil.getSessionFactory().getCurrentSession()){
            Transaction t=session.beginTransaction();
            Query<Integer> query = session.createQuery("select u.id " +
                    "from Utilisateur u " +
                    "where u.email = :email " +
                    "and u.password = :password");
            query.setParameter("email", email);
            query.setParameter("password", pwd);
            if (!query.getResultList().isEmpty()) {
                utilisateurId = query.uniqueResult();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return utilisateurId;
    }

    public boolean emailExiste(String email) {
        List<Integer> utilisateurs=new ArrayList<>();
        try(Session session= HibernateUtil.getSessionFactory().getCurrentSession()){
            Transaction t=session.beginTransaction();
            String hql = "select u.id from Utilisateur u where u.email = :email";
            utilisateurs=session.createQuery(hql).setParameter("email",email).list();
            if (!utilisateurs.isEmpty()) {
                return true;
            }}
        return false;
    }

    /*public String getNomPrenom(){
        String nomPrenom = "";
        try(Session session= HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            Query<Integer> query = session.createQuery("select u.nom, u.prenom"+" from utilisateur u"+"where u.role = 0 ");
            nomPrenom= String.valueOf(query.uniqueResult());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  nomPrenom;
    }*/

    public Utilisateur findByEmail(String email){
        Utilisateur u=null;
        try(Session session= HibernateUtil.getSessionFactory().getCurrentSession()){
            Transaction t=session.beginTransaction();
            String hql="select u "+
                    "from Utilisateur u "+
                    "where u.email= :email";
            Query<Utilisateur> query= session.createQuery(hql);
            query.setParameter("email",email);
            if (!query.getResultList().isEmpty()) {
                u = query.uniqueResult();
            }
            return u;
        }
    }
}
