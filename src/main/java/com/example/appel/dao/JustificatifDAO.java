package com.example.appel.dao;

import com.example.appel.config.HibernateUtil;
import com.example.appel.model.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class JustificatifDAO extends DAO<Justificatif> {

    private Class<Justificatif> justificatif;

    public JustificatifDAO() {
        this.justificatif = Justificatif.class;
    }

//    public static List<Boolean> existJustifPrevu(Integer seanceId, List<Etudiant> etudiantId) throws ParseException {
//        List<Justificatif> jus = new ArrayList<>();
//        boolean prevu = false;
//        List<Boolean> prevus = new ArrayList<>();
//        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
//            Transaction t = session.beginTransaction();
//            for (int i = 0; i < etudiantId.size(); i++) {
//                Etudiant id = etudiantId.get(i);
//                System.out.println("IDD: " + id);
//                String hql = "select j from Justificatif j where j.seance.id= :seanceId and j.etudiant.id= :id and j.date<j.seance.dateDebut ";
//                jus = session.createQuery(hql).setParameter("seanceId", seanceId).setParameter("id", id).list();
//                System.out.println(jus);
//                if (jus.isEmpty()) {
//                    prevu = false;
//                    System.out.println(prevu);
//                } else {
//                    prevu = true;
//                    System.out.println(prevu);
//                }
//                prevus.add(prevu);
//            }
//            return prevus;
//            //这个返回值要乱清楚 if放的位置可能有问题 导致所有返回一个true值
//        }
//    }

    public boolean existJustifPrevu(Integer seanceId, Integer etudiantId) {
        boolean prevu = false;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            String hql = "select j from Justificatif j where j.seance.id= :seanceId and j.etudiant.id= :etudiantId and j.date<j.seance.dateDebut ";
            Query<Justificatif> query = session.createQuery(hql);
            query.setParameter("seanceId", seanceId);
            query.setParameter("etudiantId", etudiantId).list();
            if (!query.getResultList().isEmpty()) {
                prevu=true;
            }
        }
        return prevu;
    }

    public Justificatif find(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            return session.get(justificatif, id);
        }
    }

    public List<Justificatif> getAllJusNonTraite() {
        List<Justificatif> jusNonTraites = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            String hql = "select j from Justificatif j " +
                    "where j.traite=false";
            jusNonTraites = session.createQuery(hql).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jusNonTraites;
    }

    //可注掉
//    public void create(Justificatif justificatif){
//        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
//            Transaction t = session.beginTransaction();
//            session.save(justificatif);
//            t.commit();
//            session.close();
//        }
//    }

    public void updateJus(Justificatif justificatif) {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            session.update(justificatif);
            t.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}