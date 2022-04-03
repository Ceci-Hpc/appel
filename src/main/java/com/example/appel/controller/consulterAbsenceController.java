package com.example.appel.controller;


import com.example.appel.dao.EtudiantDAO;

import com.example.appel.dao.UtilisateurDAO;
import com.example.appel.enumType.TypeEtudiant;
import com.example.appel.model.Etudiant;
import com.example.appel.model.Utilisateur;
import com.example.appel.model.Etat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet(name = "ConsulterAbsenceController", value = "/role/consulterAbsence")
public class consulterAbsenceController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Etudiant> etudiants;
        EtudiantDAO etudiantDAO = new EtudiantDAO();
        etudiants = etudiantDAO.getEtudiantbyTypeE(TypeEtudiant.FA);
        System.out.println(etudiants.size());
        for (Etudiant e : etudiants) {
            System.out.println(e.getNom());
        }
        System.out.println();

//        Integer Nbabsence = 0;
//
//        Integer NbabsenceJus = 0;
//        Integer NbabsenceNonJus = 0;
//        Integer NbPresence = 0;
        for (Etudiant e : etudiants) {

            e.setNbAbsence(etudiantDAO.getEtudiantNbabsence(e.getId()));
            e.setNbAbsenceJus(etudiantDAO.getEtudiantNbabsenceJus(e.getId()));
            e.setNbAbsenceNonJus(etudiantDAO.getEtudiantNbabsenceNonJus(e.getId()));
            e.setNbPresence(etudiantDAO.getEtudiantNbPresence(e.getId()));
            etudiantDAO.updateEtudiant(e);
        }


        //List<Etat> absences = etudiantDAO.getAbsencesSeanceTotal(utilisateur.getId());


        request.setAttribute("listAbsences", etudiants);
        request.getRequestDispatcher("/view/scolarite/consulterAbsence.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        List<Etudiant> etudiants;
//        EtudiantDAO etudiantDAO=new EtudiantDAO();
//        etudiants = etudiantDAO.getEtudiantbyTypeE(TypeEtudiant.FA);

        int mois = Integer.parseInt((String) req.getParameter("mois"));

        if (mois == 0) {
            List<Etudiant> etudiants;
            EtudiantDAO etudiantDAO = new EtudiantDAO();
            etudiants = etudiantDAO.getEtudiantbyTypeE(TypeEtudiant.FA);

            for (Etudiant e : etudiants) {

                e.setNbAbsence(etudiantDAO.getEtudiantNbabsence(e.getId()));
                e.setNbAbsenceJus(etudiantDAO.getEtudiantNbabsenceJus(e.getId()));
                e.setNbAbsenceNonJus(etudiantDAO.getEtudiantNbabsenceNonJus(e.getId()));
                e.setNbPresence(etudiantDAO.getEtudiantNbPresence(e.getId()));
                etudiantDAO.updateEtudiant(e);
            }
            req.setAttribute("listAbsences", etudiants);
        } else {
            List<Etudiant> etudiants;
            EtudiantDAO etudiantDAO = new EtudiantDAO();
            etudiants = etudiantDAO.getEtudiantbyTypeE(TypeEtudiant.FA);

            for (Etudiant e : etudiants) {
                e.setNbAbsence(etudiantDAO.getEtudiantNbabsenceParMois(mois, e.getId()));
                e.setNbAbsenceJus(etudiantDAO.getEtudiantNbabsenceJusParMois(mois, e.getId()));
                e.setNbAbsenceNonJus(etudiantDAO.getEtudiantNbabsenceNonJusParMois(mois, e.getId()));
                e.setNbPresence(etudiantDAO.getEtudiantNbPresenceParMois(mois, e.getId()));
                etudiantDAO.updateEtudiant(e);
                System.out.println(e.getNbAbsence());
                System.out.println(e.getNbAbsenceJus());
                System.out.println(e.getNbAbsenceNonJus());
                System.out.println(e.getNbPresence());

            }
            req.setAttribute("listAbsences", etudiants);
        }
        req.getRequestDispatcher("/view/scolarite/consulterAbsence.jsp").forward(req, resp);
    }


}
