package com.example.appel.util;

import com.github.javafaker.Faker;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import com.example.appel.dao.*;
import com.example.appel.model.*;
import com.example.appel.enumType.*;

@WebListener
public class FakeData implements ServletContextListener {

    public static final Faker FAKER = new Faker(new Locale("us"));
    public static final SimpleDateFormat SDF = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    public static UtilisateurDAO utilisateurDao = new UtilisateurDAO();
    public static EtudiantDAO etudiantDao = new EtudiantDAO();
    public static EnseignantDAO enseignantDao = new EnseignantDAO();
    public static ScolariteDAO scolariteDao = new ScolariteDAO();
    public static CoursDAO coursDao = new CoursDAO();
    public static SeanceDAO seanceDao = new SeanceDAO();
    public static JustificatifDAO justificatifDao = new JustificatifDAO();
    public static FormationDAO formationDao = new FormationDAO();
    public static FicheAppelDAO ficheAppelDao = new FicheAppelDAO();
    public static EtatDAO etatDao = new EtatDAO();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        generer();
    }

    public static void generer() {

        genererFormations();
        genererEnseignant();
        genererScolarite();
        genererCours();
        genererEtudiant();
        genererSeance();
        genererFicheAppel();
        genererJustificatif();
        genererEtat();
    }

    public static void genererFormations() {
        ArrayList<Formation> formations = new ArrayList<>(Arrays.asList(
                new Formation("Master 2 IPM"),
                new Formation("Master 2 ISIAD")
        ));
        formations.forEach(formationDao::create);
    }

    public static void genererEtudiant() {
        String prenom, nom, email, phone;
        TypeEtudiant typeEtudiant;
        GroupEtudiant groupEtudiant;
        Role role = Role.ETUDIANT;
        Formation formation = formationDao.find(1);
        etudiantDao.create(new Etudiant("zijian", "LI", "zijian.Li@ut-capitole.fr", "0697888888", "pwd", role, TypeEtudiant.FI, GroupEtudiant.TD1, formation));
        for (int i = 8; i < 16; i++) {
            typeEtudiant = TypeEtudiant.FI;
            groupEtudiant = GroupEtudiant.TD1;
            do {
                prenom = FAKER.name().firstName();
                nom = FAKER.name().lastName();
                email = prenom + "." + nom + "@ut-capitole.fr";
                phone = "0697888888";
            } while (utilisateurDao.emailExiste(email));
            etudiantDao.create(new Etudiant(prenom, nom, email, phone, "pwd", role, typeEtudiant, groupEtudiant, formation));
        }
        for (int i = 16; i < 30; i++) {
            typeEtudiant = TypeEtudiant.FI;
            groupEtudiant = GroupEtudiant.TD2;
            do {
                prenom = FAKER.name().firstName();
                nom = FAKER.name().lastName();
                email = prenom + "." + nom + "@ut-capitole.fr";
                phone = "0697888888";
            } while (utilisateurDao.emailExiste(email));
            etudiantDao.create(new Etudiant(prenom, nom, email, phone, "pwd", role, typeEtudiant, groupEtudiant, formation));
        }
        for (int i = 30; i < 56; i++) {
            typeEtudiant = TypeEtudiant.FA;
            groupEtudiant = GroupEtudiant.TD3;
            do {
                prenom = FAKER.name().firstName();
                nom = FAKER.name().lastName();
                email = prenom + "." + nom + "@ut-capitole.fr";
                phone = "0697888888";
            } while (utilisateurDao.emailExiste(email));
            etudiantDao.create(new Etudiant(prenom, nom, email, phone, "pwd", role, typeEtudiant, groupEtudiant, formation));
        }
    }

    public static void genererEnseignant() {
        Role role = Role.ENSEIGNANT;
        ArrayList<Enseignant> enseignants = new ArrayList<>(Arrays.asList(
                new Enseignant("Nathalie", "Valles", "nathalie.valles@ut-capitole.fr", "0697888888", "pwd", role),
                new Enseignant("Franck", "Ravat", "franck.ravat@ut-capitole.fr", "0697888888", "pwd", role),
                new Enseignant("Eric", "Andonoff", "eric.andonoff@ut-capitole.fr", "0697888888", "pwd", role),
                new Enseignant("Bour", "Raphaëlle", "raphaelle.bour@ut-capitole.fr", "0697888888", "pwd", role)
        ));
        enseignants.forEach(enseignantDao::create);
    }

    public static void genererScolarite() {
        Role role = Role.SCOLARITE;
        ArrayList<Scolarite> scolarites = new ArrayList<>(Arrays.asList(
                new Scolarite("Elodie", "Fontana", "elodie.fontana@ut-capitole.fr", "0697888888", "pwd", role),
                new Scolarite("Sylvie", "Cardoso", "sylvie.cardoso@ut-capitole.fr", "0697888888", "pwd", role)
        ));
        scolarites.forEach(scolariteDao::create);
    }


    public static void genererCours() {

        Formation formation1 = formationDao.find(1);
        Formation formation2 = formationDao.find(2);

        ArrayList<Cours> cours = new ArrayList<>(Arrays.asList(
                new Cours("Démarche de développement agile", 17, formation1),
                new Cours("Accompagnement Client", 20, formation1),
                new Cours("Développement d'application internet,", 15, formation1),
                new Cours("Anglais", 10, formation1),
                new Cours("Ingénierie des Processus Métiers", 17, formation2),
                new Cours("Management Agile", 11, formation2),
                new Cours("Données, conception, manipulation", 25, formation1),
                new Cours("Programmation Objet", 20, formation2),
                new Cours("Analyse et conception objet des SI", 18, formation2)
        ));
        cours.forEach(coursDao::create);
    }

    public static void genererFicheAppel() {
        for (int i = 1; i < 20; i++) {
            ficheAppelDao.create(new FicheAppel(seanceDao.find(i)));
        }
    }

    public static void genererSeance() {
        ArrayList<Seance> Seances = null;
        GroupEtudiant g1 = GroupEtudiant.TD1;
        GroupEtudiant g2 = GroupEtudiant.TD2;
        try {
            Seances = new ArrayList<>(Arrays.asList(
                    new Seance(1, "MC405", SDF.parse("31-01-2022 09:30:00"), SDF.parse("31-01-2022 12:30:00"), coursDao.find(1), enseignantDao.find(4), g1),
                    new Seance(2, "ME405", SDF.parse("03-02-2022 09:30:00"), SDF.parse("03-02-2022 12:30:00"), coursDao.find(1), enseignantDao.find(4), g1),
                    new Seance(3, "MC405", SDF.parse("04-02-2022 09:30:00"), SDF.parse("04-02-2022 12:30:00"), coursDao.find(1), enseignantDao.find(4), g1),
                    new Seance(4, "ME407", SDF.parse("07-02-2022 09:30:00"), SDF.parse("07-02-2022 12:30:00"), coursDao.find(1), enseignantDao.find(4), g1),
                    new Seance(5, "MC405", SDF.parse("08-02-2022 09:30:00"), SDF.parse("08-02-2022 12:30:00"), coursDao.find(1), enseignantDao.find(4), g2),
                    new Seance(1, "MC405", SDF.parse("09-02-2022 14:00:00"), SDF.parse("09-02-2022 17:00:00"), coursDao.find(2), enseignantDao.find(1), g2),
                    new Seance(2, "MC405", SDF.parse("09-02-2022 09:30:00"), SDF.parse("09-02-2022 12:30:00"), coursDao.find(2), enseignantDao.find(1), g2),
                    new Seance(1, "MC405", SDF.parse("10-02-2022 11:00:00"), SDF.parse("10-02-2022 12:30:00"), coursDao.find(3), enseignantDao.find(3), g2),
                    new Seance(2, "MC405", SDF.parse("10-02-2022 14:30:00"), SDF.parse("10-02-2022 17:30:00"), coursDao.find(3), enseignantDao.find(3), g2),
                    new Seance(1, "MC405", SDF.parse("11-02-2022 09:30:00"), SDF.parse("11-02-2022 12:30:00"), coursDao.find(4), enseignantDao.find(2), g1),
                    new Seance(2, "MC405", SDF.parse("11-02-2022 14:00:00"), SDF.parse("11-02-2022 15:30:00"), coursDao.find(4), enseignantDao.find(2), g1),
                    new Seance(3, "MC405", SDF.parse("14-02-2022 09:30:00"), SDF.parse("14-02-2022 12:30:00"), coursDao.find(4), enseignantDao.find(2), g2),
                    new Seance(7, "MC405", SDF.parse("25-03-2022 11:00:00"), SDF.parse("25-03-2022 12:00:00"), coursDao.find(12), enseignantDao.find(1), g2),
                    new Seance(5, "MC405", SDF.parse("01-04-2022 09:30:00"), SDF.parse("01-04-2022 12:30:00"), coursDao.find(4), enseignantDao.find(2), g2),
                    new Seance(8, "ME405", SDF.parse("30-03-2022 10:00:00"), SDF.parse("30-03-2022 12:30:00"), coursDao.find(2), enseignantDao.find(1), g1),
                    new Seance(9, "ME407", SDF.parse("01-04-2022 14:00:00"), SDF.parse("01-04-2022 17:00:00"), coursDao.find(2), enseignantDao.find(1), g1)
                    ));
            Seances.forEach(seanceDao::create);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void genererJustificatif() {
        for (int i = 0; i < 6; i++) {
            justificatifDao.create(new Justificatif());
        }
    }

    public static void genererEtat() {
        EtatAppel etatAppel1 = EtatAppel.PRESENCE;
        EtatAppel etatAppel2 = EtatAppel.ABSENCE;
        EtatAppel etatAppel3 = EtatAppel.ABSENCE_JUSTIFIE;
        EtatAppel etatAppel4 = EtatAppel.NON_JUSTIFIE;
        etatDao.create(new Etat(etudiantDao.find(8), ficheAppelDao.find(2), etatAppel2));
        etatDao.create(new Etat(etudiantDao.find(9), ficheAppelDao.find(2), etatAppel1));
        etatDao.create(new Etat(etudiantDao.find(10), ficheAppelDao.find(2), etatAppel1));
        etatDao.create(new Etat(etudiantDao.find(11), ficheAppelDao.find(2), etatAppel1));
        etatDao.create(new Etat(etudiantDao.find(8), ficheAppelDao.find(3), etatAppel2));
        etatDao.create(new Etat(etudiantDao.find(8), ficheAppelDao.find(4), etatAppel2));
        etatDao.create(new Etat(etudiantDao.find(12), ficheAppelDao.find(4), etatAppel1));
        etatDao.create(new Etat(etudiantDao.find(11), ficheAppelDao.find(4), etatAppel1));
        etatDao.create(new Etat(etudiantDao.find(13), ficheAppelDao.find(4), etatAppel1));
        etatDao.create(new Etat(etudiantDao.find(10), ficheAppelDao.find(5), etatAppel1));
        etatDao.create(new Etat(etudiantDao.find(12), ficheAppelDao.find(5), etatAppel1));
        etatDao.create(new Etat(etudiantDao.find(13), ficheAppelDao.find(5), etatAppel2));
        etatDao.create(new Etat(etudiantDao.find(8), ficheAppelDao.find(6), etatAppel2));
        etatDao.create(new Etat(etudiantDao.find(10), ficheAppelDao.find(6), etatAppel1));
        etatDao.create(new Etat(etudiantDao.find(11), ficheAppelDao.find(6), etatAppel1));
        etatDao.create(new Etat(etudiantDao.find(9), ficheAppelDao.find(3), etatAppel4));
        etatDao.create(new Etat(etudiantDao.find(30), ficheAppelDao.find(3), etatAppel2));
        etatDao.create(new Etat(etudiantDao.find(33), ficheAppelDao.find(3), etatAppel2));
        etatDao.create(new Etat(etudiantDao.find(35), ficheAppelDao.find(3), etatAppel2));
        etatDao.create(new Etat(etudiantDao.find(37), ficheAppelDao.find(3), etatAppel2));
        etatDao.create(new Etat(etudiantDao.find(38), ficheAppelDao.find(3), etatAppel1));
        etatDao.create(new Etat(etudiantDao.find(41), ficheAppelDao.find(3), etatAppel4));
        etatDao.create(new Etat(etudiantDao.find(40), ficheAppelDao.find(3), etatAppel3));
        etatDao.create(new Etat(etudiantDao.find(7), ficheAppelDao.find(3), etatAppel4));
        etatDao.create(new Etat(etudiantDao.find(7), ficheAppelDao.find(13), etatAppel4));
        etatDao.create(new Etat(etudiantDao.find(13), ficheAppelDao.find(13), etatAppel1));
        etatDao.create(new Etat(etudiantDao.find(13), ficheAppelDao.find(3), etatAppel4));
        etatDao.create(new Etat(etudiantDao.find(13), ficheAppelDao.find(4), etatAppel4));
        etatDao.create(new Etat(etudiantDao.find(13), ficheAppelDao.find(5), etatAppel4));
        etatDao.create(new Etat(etudiantDao.find(13), ficheAppelDao.find(2), etatAppel4));
        etatDao.create(new Etat(etudiantDao.find(7), ficheAppelDao.find(13), etatAppel2));
    }
}
