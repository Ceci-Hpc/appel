package com.example.appel.controller;

import com.example.appel.dao.UtilisateurDAO;
import com.example.appel.enumType.Role;
import com.example.appel.model.Utilisateur;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "ConnexionController", value = "/connexion")
public class connexionController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/connexion.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<String, String> erreurs = new HashMap<>();

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (email == null || email.isEmpty()) {
            erreurs.put("email_erreur", "Veillez entrer l'addresse email");
        }
        if (password == null || password.isEmpty()) {
            erreurs.put("password_erreur", "Veillez entrer un mot de passe");
        }

        if (erreurs.isEmpty()) {
            try {
                UtilisateurDAO utilisateurDao = new UtilisateurDAO();
                Integer utilisateurId = utilisateurDao.ConnectUser(email,password);
                if (utilisateurId == null) {
                    req.setAttribute("generale_erreur", "Email ou mot de passe incorrect");
                    req.getRequestDispatcher("/view/connexion.jsp").forward(req, resp);
                } else {
                    req.getSession().setAttribute("auth", utilisateurId);
                    //req.getRequestDispatcher("/role").forward(req, resp);
                    resp.sendRedirect("/role");
                }
            } catch (Exception e) {
                e.printStackTrace();
                req.setAttribute("generale_erreur", "Erreur technique");
                req.getRequestDispatcher("/view/connexion.jsp").forward(req, resp);
            }

        } else {
            erreurs.forEach(req::setAttribute);
            req.getRequestDispatcher("/view/connexion.jsp").forward(req, resp);
        }
    }
}
