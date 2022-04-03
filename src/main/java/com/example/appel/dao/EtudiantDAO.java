package com.example.appel.dao;

import com.example.appel.config.HibernateUtil;
import com.example.appel.enumType.GroupEtudiant;
import com.example.appel.enumType.TypeEtudiant;
import com.example.appel.model.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class EtudiantDAO extends DAO<Etudiant> {

    private Class<Etudiant> etudiant;

    public EtudiantDAO() {
        this.etudiant = Etudiant.class;
    }

    public Etudiant find(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            return session.get(etudiant, id);
        }
    }

    public List<Etat> getAbsencesSeanceTotal(Integer etudiantId) {
        List<Etat> absences = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            String hql = "select distinct et " +
                    "from Etat et " +
                    "where et.etudiant.id= :etudiantId " +
                    "and (et.etatAppel='ABSENCE' or et.etatAppel='ABSENCE_JUSTIFIE' or et.etatAppel='NON_JUSTIFIE' or et.etatAppel='ABSENCE_SIGNALEE')";
            absences = session.createQuery(hql).setParameter("etudiantId", etudiantId).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return absences;
    }

    public void updateEtudiant(Etudiant etudiant) {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            session.update(etudiant);
            t.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Etat> getAbsencesSeanceParMois(Integer etudiantId, Integer mois) {
        List<Etat> absences = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            String hql = "select distinct et " +
                    "from Etat et , FicheAppel f, Seance s " +
                    "where et.ficheAppel.id = f.id " +
                    "and f.seance.id = s.id " +
                    "and et.etudiant.id= :etudiantId " +
                    "and Month(dateDebut)= :mois " +
                    "and (et.etatAppel='ABSENCE' or et.etatAppel='ABSENCE_JUSTIFIE' or et.etatAppel='NON_JUSTIFIE' or et.etatAppel='ABSENCE_SIGNALEE')";
            absences = session.createQuery(hql).setParameter("etudiantId", etudiantId).setParameter("mois", mois).list();
            //absences= session.createQuery(hql).setParameter("mois",mois).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return absences;
    }

    public List<Seance> getSeances(Integer etudiantId) {
        List<Seance> seances = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            String hql = "select distinct s " +
                    "from Seance s, Cours c, Etudiant e " +
                    "where s.cours.id=c.id " +
                    "and c.formation.id=e.formation.id " +
                    "and e.id= :etudiantId " +
                    "and s.groupeTD= e.groupEtudiant";
            seances = session.createQuery(hql).setParameter("etudiantId", etudiantId).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return seances;
    }

    public List<Etudiant> getNomEtu() {
        List<Etudiant> etudiants = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            String hql = "select e from Etudiant e, Justificatif  j where j.etudiant.id=e.id ";
            etudiants = session.createQuery(hql).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return etudiants;

    }

    public List<Etudiant> getEtudiantbyTypeE(TypeEtudiant type) {
        List<Etudiant> etudiants = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            String hql = "select distinct e " +
                    "from Etudiant e,Etat et " +
                    "where e.id =et.etudiant.id " +
                    "and e.typeEtudiant = 'FA' ";
            //etudiants = session.createQuery(hql).setParameter("type", type).list();
            etudiants = session.createQuery(hql).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return etudiants;
    }

    //consulter les absences d'un etudiant
    public List<Etat> absencesEtu(Integer idEtu) {
        List<Etat> absence = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            String hql = "select et " +
                    "from Etat et " +
                    "where et.etudiant.id= :idEtu " +
                    "and (et.etatAppel='ABSENCE' or et.etatAppel='ABSENCE_JUSTIFIE' or et.etatAppel='NON_JUSTIFIE' or et.etatAppel='ABSENCE_SIGNALEE' )";
            absence = session.createQuery(hql).setParameter("idEtu", idEtu).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return absence;
    }

    //consulter les retards d'un etudiant
    public List<Etat> retardsEtu(Integer idEtu) {
        List<Etat> retard = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            String hql = "select et " +
                    "from Etat et " +
                    "where et.etudiant.id= :idEtu " +
                    "and et.etatAppel='RETARD'";
            retard = session.createQuery(hql).setParameter("idEtu", idEtu).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retard;
    }

    //consulter les absences d'un etudiant d'un cours
    public List<Etat> absencesEtuCours(Integer idEtu, String nomCours) {
        List<Etat> absence = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            String hql = "select et " +
                    "from Etat et " +
                    "where et.etudiant.id= ? " +
                    "and et.ficheAppel.seance.cours.nomCours like ? " +
                    "and (et.etatAppel='ABSENCE' or et.etatAppel='ABSENCE_JUSTIFIE' or et.etatAppel='NON_JUSTIFIE' or et.etatAppel='ABSENCE_SIGNALEE' )";
            absence = session.createQuery(hql).setParameter(0, idEtu).setParameter(1, "%" + nomCours + "%").list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return absence;
    }

    //consulter les absences d'un etudiant d'un cours
    public List<Etat> retardsEtuCours(Integer idEtu, String nomCours) {
        List<Etat> retard = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            String hql = "select et " +
                    "from Etat et " +
                    "where et.etudiant.id= ? " +
                    "and et.ficheAppel.seance.cours.nomCours like ? " +
                    "and et.etatAppel='RETARD'";
            retard = session.createQuery(hql).setParameter(0, idEtu).setParameter(1, "%" + nomCours + "%").list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retard;
    }


    public Integer getEtudiantNbabsence(int etudiantId) {
        Integer nb = 0;


        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {

            Transaction t = session.beginTransaction();
            Query<Integer> query = session.createQuery("select count(et.id) " +
                    "from Etudiant e,Etat et " +
                    "where e.id =et.etudiant.id " +
                    "and e.id = :etudiantId " +
                    "and et.etatAppel='ABSENCE' ");
            query.setParameter("etudiantId", etudiantId);
            if (!query.getResultList().isEmpty()) {

                nb = Integer.parseInt(String.valueOf(query.uniqueResult()));

//                Query query1 =session.createQuery("update Etudiant e "+
//                        " set e.nbAbsence =:nb "
//                        +"where e.id=:etudiantId ");
//                query1.setParameter("nb",nb);
//                query1.setParameter("etudiantId",etudiantId);
//                query1.executeUpdate();
//                t.commit();
            } else {
                nb = 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(nb);
        return (nb);
    }

    public Integer getEtudiantNbabsenceParMois(int mois, int etudiantId) {
        Integer nb = 0;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            Query<Integer> query = session.createQuery("select count(et.id) " +
                    "from Etudiant e,Etat et,FicheAppel f, Seance s " +
                    "where e.id =et.etudiant.id " +
                    "and et.ficheAppel.id = f.id " +
                    "and f.seance.id = s.id " +
                    "and e.id = :etudiantId " +
                    "and et.etatAppel='ABSENCE' " +
                    "and Month(s.dateDebut) =:mois");
            query.setParameter("etudiantId", etudiantId);
            query.setParameter("mois", mois);
            if (!query.getResultList().isEmpty()) {
                nb = Integer.parseInt(String.valueOf(query.uniqueResult()));
            } else {
                nb = 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(nb);
        return (nb);
    }

    public Integer getEtudiantNbabsenceJus(int etudiantId) {
        Integer nb = 0;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {

            Transaction t = session.beginTransaction();
            Query<Integer> query = session.createQuery("select count(et.id) " +
                    "from Etudiant e,Etat et " +
                    "where e.id =et.etudiant.id " +
                    "and e.id = :etudiantId " +
                    "and et.etatAppel='ABSENCE_JUSTIFIE' ");
            query.setParameter("etudiantId", etudiantId);
            if (!query.getResultList().isEmpty()) {
                nb = Integer.parseInt(String.valueOf(query.uniqueResult()));

            } else {
                nb = 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return nb;
    }

    public Integer getEtudiantNbabsenceJusParMois(int mois, int etudiantId) {
        Integer nb = 0;

        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {

            Transaction t = session.beginTransaction();
            Query<Integer> query = session.createQuery("select count(et.id) " +
                    "from Etudiant e,Etat et " +
                    "where e.id =et.etudiant.id " +
                    "and e.id = :etudiantId " +
                    "and et.etatAppel='ABSENCE_JUSTIFIE' " +
                    "and Month(et.ficheAppel.seance.dateDebut) =:mois");
            query.setParameter("etudiantId", etudiantId);
            query.setParameter("mois", mois);
            if (!query.getResultList().isEmpty()) {

                nb = Integer.parseInt(String.valueOf(query.uniqueResult()));

            } else {
                nb = 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(nb);
        return (nb);
    }

    public Integer getEtudiantNbabsenceNonJus(int etudiantId) {
        Integer nb = 0;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {

            Transaction t = session.beginTransaction();
            Query<Integer> query = session.createQuery("select count(et.id) " +
                    "from Etudiant e,Etat et " +
                    "where e.id =et.etudiant.id " +

                    "and e.id = :etudiantId " +
                    "and et.etatAppel='NON_JUSTIFIE' ");
            query.setParameter("etudiantId", etudiantId);
            if (!query.getResultList().isEmpty()) {
                nb = Integer.parseInt(String.valueOf(query.uniqueResult()));

            } else {
                nb = 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return nb;
    }

    public Integer getEtudiantNbabsenceNonJusParMois(int mois, int etudiantId) {
        Integer nb = 0;

        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {

            Transaction t = session.beginTransaction();
            Query<Integer> query = session.createQuery("select count(et.id) " +
                    "from Etudiant e,Etat et, FicheAppel f,Seance s " +
                    "where e.id =et.etudiant.id " +
                    "and et.ficheAppel.id = f.id " +
                    "and f.seance.id = s.id " +
                    "and e.id = :etudiantId " +
                    "and et.etatAppel='NON_JUSTIFIE' " +
                    "and et.ficheAppel.seance.dateDebut =:mois");
            query.setParameter("etudiantId", etudiantId);
            query.setParameter("mois", mois);
            if (!query.getResultList().isEmpty()) {

                nb = Integer.parseInt(String.valueOf(query.uniqueResult()));

            } else {
                nb = 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(nb);
        return (nb);
    }

    public Integer getEtudiantNbPresence(int etudiantId) {
        Integer nb = 0;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {

            Transaction t = session.beginTransaction();
            Query<Integer> query = session.createQuery("select count(et.id) " +
                    "from Etudiant e,Etat et " +
                    "where e.id =et.etudiant.id " +
                    "and e.id = :etudiantId " +
                    "and et.etatAppel='PRESENCE' ");
            query.setParameter("etudiantId", etudiantId);
            if (!query.getResultList().isEmpty()) {
                nb = Integer.parseInt(String.valueOf(query.uniqueResult()));

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return nb;
    }

    public Integer getEtudiantNbPresenceParMois(int mois, int etudiantId) {
        Integer nb = 0;

        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {

            Transaction t = session.beginTransaction();
            Query<Integer> query = session.createQuery("select count(et.id) " +
                    "from Etudiant e,Etat et,FicheAppel f,Seance s " +
                    "where e.id =et.etudiant.id " +
                    "and et.ficheAppel.id = f.id " +
                    "and f.seance.id = s.id " +
                    "and e.id = :etudiantId " +
                    "and et.etatAppel='PRESENCE' " +
                    "and Month(s.dateDebut) =:mois");
            query.setParameter("etudiantId", etudiantId);
            query.setParameter("mois", mois);
            if (!query.getResultList().isEmpty()) {
                nb = Integer.parseInt(String.valueOf(query.uniqueResult()));
            } else {
                nb = 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(nb);
        return (nb);
    }

    public List<Etudiant> getEtudiantAbsenceSup3() {
        List<Etudiant> etudiants = new ArrayList<>();
        //HashMap<Etudiant, Integer> nbabsence = new HashMap<>();
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            String hql =    "select distinct e "+
                    "from Etudiant e,Etat et, FicheAppel f, Seance s,Cours c " +
                    "where e.id =et.etudiant.id " +
                    "and et.ficheAppel.id =f.id "+
                    "and f.seance.id = s.id "+
                    "and s.cours.id = c.id "+
                    "and et.etatAppel='NON_JUSTIFIE' " +
                    "having count(et.id)>3 "
                    ;

            //etudiants = session.createQuery(hql).setParameter("type", type).list();
            etudiants = session.createQuery(hql).list();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return etudiants;
    }

    public Integer getNbAbsenceParCoursEtu(int etudiantId, Integer coursId){
        Integer nb = 0 ;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            Query query = session.createQuery("select count(et.id) "+
                    "from Etudiant e,Etat et, FicheAppel f, Seance s,Cours c " +
                    "where e.id =et.etudiant.id " +
                    "and et.ficheAppel.id =f.id "+
                    "and f.seance.id = s.id "+
                    "and s.cours.id = c.id "+
                    "and c.id = :coursId "+
                    "and e.id = :etudiantId "+
                    "and et.etatAppel='NON_JUSTIFIE' "
            );
            query.setParameter("etudiantId", etudiantId);
            query.setParameter("coursId", coursId);
            //etudiants = session.createQuery(hql).setParameter("type", type).list();
            if (!query.getResultList().isEmpty()) {
                nb = Integer.parseInt(String.valueOf(query.uniqueResult())) ;
            }
            else{
                nb=0;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(nb);
        return nb;
    }

    public Integer getNbAbsenceEtu(int etudiantId){
        Integer nb = 0 ;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            Query query = session.createQuery("select count(et.id) "+
                    "from Etudiant e,Etat et, FicheAppel f, Seance s,Cours c " +
                    "where e.id =et.etudiant.id " +
                    "and et.ficheAppel.id =f.id "+
                    "and f.seance.id = s.id "+
                    "and s.cours.id = c.id "+
                    "and e.id = :etudiantId "+
                    "and et.etatAppel='NON_JUSTIFIE' "
            );
            query.setParameter("etudiantId", etudiantId);

            //etudiants = session.createQuery(hql).setParameter("type", type).list();
            if (!query.getResultList().isEmpty()) {
                nb = Integer.parseInt(String.valueOf(query.uniqueResult())) ;
            }
            else{
                nb=0;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(nb);
        return nb;

    }
}



