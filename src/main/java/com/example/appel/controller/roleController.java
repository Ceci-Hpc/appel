package com.example.appel.controller;

import com.example.appel.dao.SeanceDAO;
import com.example.appel.dao.UtilisateurDAO;
import com.example.appel.enumType.Role;
import com.example.appel.model.Seance;
import com.example.appel.model.Utilisateur;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet(name = "roleController", value = "/role")
public class roleController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer utilisateurId=(Integer) req.getSession().getAttribute("auth");
        UtilisateurDAO utilisateurDAO=new UtilisateurDAO();
        Utilisateur u=utilisateurDAO.find(utilisateurId);
        req.setAttribute("utilisateur",u);
        Role role=u.getRole();
        if (role == Role.ETUDIANT) {
            //req.getRequestDispatcher("/view/etudiant/accueilEtu.jsp").forward(req, resp);
            resp.sendRedirect("/role/emploi");
        } else if (role == Role.ENSEIGNANT) {
            //req.getRequestDispatcher("/view/page/planning.jsp").forward(req, resp);
            SeanceDAO seanceDAO=new SeanceDAO();
            Seance seance=seanceDAO.getSeanceRecent(utilisateurId,new Date());
            req.getSession().setAttribute("seance",seance);
            if(seance!=null){
                resp.sendRedirect("/role/appel");
            }else{
                resp.sendRedirect("/role/emploi");
            }
        } else if (role == Role.SCOLARITE) {
            req.getRequestDispatcher("/view/scolarite/acceuilSco.jsp").forward(req, resp);
        }else{
            req.getRequestDispatcher("/view/connexion.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
