package com.example.appel.controller;

import com.example.appel.dao.EtatDAO;
import com.example.appel.dao.EtudiantDAO;
import com.example.appel.model.Etat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ConsultationAbsCtrl", value = "/role/consultationAbs")
public class consultationAbsController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/enseignant/consultationAbs.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nomEtu = req.getParameter("nomEtu");
        String nomCours = req.getParameter("nomCours");
        String msg_erreur = "";

        System.out.println(nomEtu);
        System.out.println(nomCours);

        if (nomEtu.isEmpty()){
            msg_erreur = "Veuillez saisir un nom d'étudiant!";

            req.setAttribute("erreur", msg_erreur);
            req.getRequestDispatcher("/view/enseignant/consultationAbs.jsp").forward(req,resp);
        }
        else if (nomCours.isEmpty()){
            //get
            EtudiantDAO etu = new EtudiantDAO();
            List<Etat> absence = etu.absencesEtu(etu.find(Integer.valueOf(nomEtu)).getId());
            List<Etat> retard = etu.retardsEtu(etu.find(Integer.valueOf(nomEtu)).getId());
            req.setAttribute("absence",absence);
            req.setAttribute("retard",retard);
            req.getRequestDispatcher("/view/enseignant/consultationAbs.jsp").forward(req, resp);
        }
        else{
            EtudiantDAO etuCours = new EtudiantDAO();
            List<Etat> absence = etuCours.absencesEtuCours(etuCours.find(Integer.valueOf(nomEtu)).getId(),nomCours);
            List<Etat> retard = etuCours.retardsEtuCours(etuCours.find(Integer.valueOf(nomEtu)).getId(),nomCours);
            req.setAttribute("absence",absence);
            req.setAttribute("retard",retard);
            req.getRequestDispatcher("/view/enseignant/consultationAbs.jsp").forward(req, resp);
        }
    }

}

