package com.example.appel.dao;

import com.example.appel.config.HibernateUtil;
import com.example.appel.enumType.GroupEtudiant;
import com.example.appel.model.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class SeanceDAO extends DAO<Seance> {
    public static final SimpleDateFormat DF = new SimpleDateFormat("dd-MM-yyyy HH:mm");
    private Class<Seance> seance;

    public SeanceDAO() {
        this.seance = Seance.class;
    }

    public Seance find(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            return session.get(seance, id);
        }
    }

    public List<Etudiant> getAllEtudiants(Integer seanceId) {
        List<Etudiant> etudiants = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            String hql = "select e " +
                    "from Seance s,Cours c, Etudiant e, Formation f " +
                    "where s.cours.id=c.id " +
                    "and c.formation.id=f.id and f.id=e.formation.id " +
                    "and s.id= :seanceId and s.groupeTD=e.groupEtudiant";
            etudiants = session.createQuery(hql).setParameter("seanceId", seanceId).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return etudiants;
    }

    public List<Etudiant> getAllEtudiantsParseance(Integer coursId, Integer numSeance) {
        List<Etudiant> etudiants = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            String hql = "select e " +
                    "from Cours c,Etudiant e,Etat et,Seance s, FicheAppel f " +
                    "where e.id =et.etudiant.id " +
                    "and et.ficheAppel.id =f.id "+
                    "and f.seance.id = s.id "+
                    "and s.cours.id = c.id "+
                    "and c.id= :coursId "+
                    "and s.numSeance = :numSeance";
            etudiants = session.createQuery(hql).setParameter("coursId", coursId).setParameter("numSeance",numSeance).list();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return etudiants;
    }

    public List<Etudiant> getAbsences(Integer seanceId) {
        List<Etudiant> absences = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            String hql = "select distinct e " +
                    "from Seance s, Etat et, Etudiant e " +
                    "where s.ficheAppel.id=et.ficheAppel.id " +
                    "and s.id= :seanceId " +
                    "and et.etudiant.id=e.id and s.groupeTD=e.groupEtudiant " +
                    "and (et.etatAppel='ABSENCE' or et.etatAppel='ABSENCE_JUSTIFIE' or et.etatAppel='NON_NOTIFIE' or et.etatAppel='ABSENCE_SIGNALEE')";
            absences = session.createQuery(hql).setParameter("seanceId", seanceId).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return absences;
    }

    public Seance getSeanceRecent(Integer enseignantId, Date temps) {
        Seance seance = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            String hql = "select s " +
                    "from Seance s " +
                    "where s.enseignant.id= :enseignantId " +
                    "and s.dateDebut < :temps " +
                    "and s.dateFin> :temps";
            Query<Seance> query = session.createQuery(hql);
            query.setParameter("enseignantId", enseignantId);
            query.setParameter("temps", temps);
            if (!query.getResultList().isEmpty()) {
                seance = query.uniqueResult();
            }
        }
        return seance;
    }

    public static void insertSeance(String dateDebut, String dateFin, int numSeance, String salle, int cours_id, int enseignant_id, GroupEtudiant groupeTD) {

        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            Cours cours = session.get(Cours.class,cours_id);
            Enseignant enseignant = session.get(Enseignant.class,enseignant_id);
            Seance seance = new Seance(numSeance,salle,DF.parse(dateDebut),DF.parse(dateFin),cours,enseignant,groupeTD);
            //提出新创建的seance自增id并加入fiche
            session.save(seance);
            t.commit();
            session.close();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
