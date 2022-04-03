package com.example.appel.config;

import com.example.appel.model.*;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Chargement de la configuration et création de la SessionFactory.
 * (hibernate.cfg.xml)
 */
public class HibernateUtil {

    private static final SessionFactory SESSION_FACTORY;

    static {
        try	{
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            System.out.println("Hibernate Configuration loaded");

            // Entités
            configuration.addAnnotatedClass(Formation.class);
            configuration.addAnnotatedClass(Utilisateur.class);
            configuration.addAnnotatedClass(Enseignant.class);
            configuration.addAnnotatedClass(Etudiant.class);
            configuration.addAnnotatedClass(Scolarite.class);
            configuration.addAnnotatedClass(FicheAppel.class);
            configuration.addAnnotatedClass(Cours.class);
            configuration.addAnnotatedClass(Seance.class);
            configuration.addAnnotatedClass(Justificatif.class);
            configuration.addAnnotatedClass(Etat.class);
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            System.out.println("Hibernate serviceRegistry created");

            SESSION_FACTORY = configuration.buildSessionFactory(serviceRegistry);
        } catch (HibernateException ex) {
            System.err.println("Initial SessionFactory creation failed.\n" + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory () {
        return SESSION_FACTORY;
    }

}