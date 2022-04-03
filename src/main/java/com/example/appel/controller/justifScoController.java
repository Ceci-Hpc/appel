package com.example.appel.controller;

import com.example.appel.dao.EtudiantDAO;
import com.example.appel.dao.JustificatifDAO;
import com.example.appel.model.Etudiant;
import com.example.appel.model.Justificatif;
import com.example.appel.model.Utilisateur;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "justifScoController", value = "/role/justificatifSco")
public class justifScoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JustificatifDAO justificatifDAO = new JustificatifDAO();
        List<Justificatif> justificatifs = justificatifDAO.getAllJusNonTraite();
        req.setAttribute("justificatifs", justificatifs);
        req.setAttribute("nbrJust",justificatifs.size());
        req.getRequestDispatcher("/view/scolarite/verifierJust.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JustificatifDAO justificatifDAO=new JustificatifDAO();
        int id = Integer.parseInt(req.getParameter("id"));
        boolean valid = true;
        boolean traite = true;
        Justificatif just = null;
        just = justificatifDAO.find(id);
        just.setValidee(valid);
        just.setTraite(traite);
        justificatifDAO.updateJus(just);
        System.out.println("valider");

        resp.sendRedirect("/view/scolarite/acceuilSco.jsp");

    }
}
