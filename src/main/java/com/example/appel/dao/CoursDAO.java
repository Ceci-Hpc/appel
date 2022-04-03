package com.example.appel.dao;

import com.example.appel.config.HibernateUtil;
import com.example.appel.model.Cours;
import com.example.appel.model.Etudiant;
import com.example.appel.model.Seance;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class CoursDAO extends DAO<Cours>{

    private Class<Cours> cours;
    public CoursDAO(){
        this.cours=Cours.class;
    }

    public Cours find(Integer id){
        try(Session session= HibernateUtil.getSessionFactory().getCurrentSession()){
            Transaction t=session.beginTransaction();
            return session.get(cours, id);
        }
    }

    public Cours findByName(String nomCours){
        List<Cours> cours=new ArrayList<>();
        try(Session session= HibernateUtil.getSessionFactory().getCurrentSession()){
            Transaction t=session.beginTransaction();
            String hql="select c "+
                    "from Cours c "+
                    "where c.nomCours= :nomCours";
            cours=session.createQuery(hql).setParameter("nomCours",nomCours).list();
            return cours.get(0);
        }
    }

    public List<Seance> getSeanceCours(Integer coursID) {
        //trouver le cours et la seance par cours
        List<Seance> seances = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            Cours c = session.get(Cours.class, coursID);
            seances = c.getSeances();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return seances;
    }

    public List<Etudiant> getAbsencesUp3(Integer coursId) {
        List<Etudiant> absences = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            String hql="select distinct e "+
                    "from Etudiant e, Etat et, Seance s "+
                    "where e.id=et.etudiant.id "+
                    "and et.ficheAppel.seance.id=s.id "+
                    "and s.cours.id= :coursId " +
                    "and (et.etatAppel='NON_JUSTIFIE' )"+
                    "group by e.id " +
                    "having count(*)>3";
            absences=session.createQuery(hql).setParameter("coursId",coursId).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return absences;
    }

    public List<Cours> getCoursEtudiant(Integer etudiantId){
        List<Cours> cours= new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            String hql="select distinct c "+
                    "from Cours c,Etudiant e "+
                    "where c.formation.id=e.formation.id "+
                    "and e.id= :etudiantId";
            cours=session.createQuery(hql).setParameter("etudiantId",etudiantId).list();
            return cours;
        }
    }

    public Integer getCoursAbsenceTotalParCours(Integer coursId){
        Integer nb = 0;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            Query<Integer> query=session.createQuery("select count(DISTINCT et.id)"+
                    "from Cours c,Etudiant e,Etat et, FicheAppel f, Seance s "+
                    "where e.id =et.etudiant.id " +
                    "and et.ficheAppel.id =f.id "+
                    "and f.seance.id = s.id "+
                    "and s.cours.id = c.id "+
                    "and c.id= :coursId " +
                    "and et.etatAppel = 'NON_JUSTIFIE' "
            );
            query.setParameter("coursId", coursId);
            if (!query.getResultList().isEmpty()) {
                nb = Integer.parseInt(String.valueOf(query.uniqueResult()));
            }
            else{
                nb=0;
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(nb);
        return (nb);

    }
    public Integer getCoursAbsenceTotal(){
        Integer nb = 0;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            Query<Integer> query=session.createQuery("select count(DISTINCT et.id)"+
                    "from Cours c,Etudiant e,Etat et,FicheAppel f, Seance s  "+
                    "where e.id =et.etudiant.id " +
                    "and et.ficheAppel.id =f.id "+
                    "and f.seance.id = s.id "+
                    "and s.cours.id = c.id "+
                    "and et.etatAppel = 'NON_JUSTIFIE' "
            );

            if (!query.getResultList().isEmpty()) {
                nb = Integer.parseInt(String.valueOf(query.uniqueResult()));
            }
            else{
                nb=0;
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(nb);
        return (nb);

    }

    public Integer getCoursAbsenceParSeance(Integer coursId, Integer numSeance){
        Integer nb = 0;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            Query<Integer> query=session.createQuery("select count(et.id)"+
                    "from Cours c,Etudiant e,Etat et,Seance s, FicheAppel f " +
                    "where e.id =et.etudiant.id " +
                    "and et.ficheAppel.id =f.id "+
                    "and f.seance.id = s.id "+
                    "and s.cours.id = c.id "+
                    "and c.id= :coursId " +
                    "and et.etatAppel='NON_JUSTIFIE' "+
                    "and s.numSeance = :numSeance "
            );
            query.setParameter("coursId", coursId);
            query.setParameter("numSeance",numSeance);
            if (!query.getResultList().isEmpty()) {
                nb = Integer.parseInt(String.valueOf(query.uniqueResult()));
            }
            else{
                nb=0;
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(nb);
        return (nb);

    }

    public Integer getNombreSeanceParCours(Integer coursId){
        Integer nb = 0;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            Query<Integer> query=session.createQuery("select count(s.id) "+
                    "from Cours c,Seance s "+
                    "where c.id=s.cours.id "+
                    "and c.id= :coursId "
            );
            query.setParameter("coursId", coursId);
            if (!query.getResultList().isEmpty()) {
                nb = Integer.parseInt(String.valueOf(query.uniqueResult()));
            }
            else{
                nb=0;
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(nb);
        return (nb);

    }

    public Integer getNombreSeance(){
        Integer nb = 0;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            Query<Integer> query=session.createQuery("select count(s.id) "+
                    "from Cours c,Seance s "+
                    "where c.id=s.cours.id "
            );

            if (!query.getResultList().isEmpty()) {
                nb = Integer.parseInt(String.valueOf(query.uniqueResult()));
            }
            else{
                nb=0;
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(nb);
        return (nb);

    }

    public List<Cours> getAllCours(){
        List<Cours> cours = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            String hql="select distinct c "+
                    "from Cours c ";
            cours=session.createQuery(hql).list();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return cours;

    }
}
