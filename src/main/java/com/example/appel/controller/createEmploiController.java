package com.example.appel.controller;

import com.example.appel.dao.CoursDAO;
import com.example.appel.dao.EnseignantDAO;
import com.example.appel.dao.FicheAppelDAO;
import com.example.appel.dao.SeanceDAO;
import com.example.appel.enumType.GroupEtudiant;
import com.example.appel.model.FicheAppel;
import com.example.appel.model.Seance;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "createEmploiController", value = "/role/createEmploi")
public class createEmploiController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/view/scolarite/createEmploi.jsp").forward(req,resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String msg = " ";

        String dateDebut = req.getParameter("Date debut");
    String dateFin = req.getParameter("Date fin");
    int numSeance = Integer.parseInt(req.getParameter("Numero seance"));
    String salle = req.getParameter("Salle");
    int coursID = Integer.parseInt(req.getParameter("coursID"));
    int enseignantID = Integer.parseInt(req.getParameter("enseignantID"));
    String groupeTD = req.getParameter("GroupeTD");

        if (dateDebut.isEmpty())
            msg = "Veuillez saisir une date et heure de debut";
        if (dateFin.isEmpty())
            msg = "Veuillez saisir une date et heure de fin";//si on met rien sur les deux???
        if (salle.isEmpty())
            msg = "Veuillez saisir une salle";
        req.setAttribute("avert",msg);

 try {
     CoursDAO coursDAO = new CoursDAO();
     EnseignantDAO enseignantDAO = new EnseignantDAO();
     DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     Date dateDebut1 = fmt.parse(dateDebut);
     Date dateFin1 = fmt.parse(dateFin);
     Seance s = new Seance();
     s.setCours(coursDAO.find(coursID));
     s.setDateDebut(dateDebut1);
     s.setDateFin(dateFin1);
     s.setNumSeance(numSeance);
     s.setSalle(salle);
     s.setEnseignant(enseignantDAO.find(enseignantID));
     s.setGroupeTD(GroupEtudiant.valueOf(groupeTD));
     SeanceDAO seanceDAO = new SeanceDAO();
     seanceDAO.create(s);

     FicheAppelDAO ficheAppelDAO = new FicheAppelDAO();
     FicheAppel f = new FicheAppel();
     f.setSeance(s);
     ficheAppelDAO.create(f);
     //chainage vers acceuil scolarite
     req.getRequestDispatcher("/view/scolarite/acceuilSco.jsp").forward(req,resp);
 } catch (Exception e) {
     e.printStackTrace();
 }
    }

    }

