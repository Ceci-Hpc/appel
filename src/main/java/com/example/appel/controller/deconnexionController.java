package com.example.appel.controller;

import com.example.appel.model.Utilisateur;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "deconnexionController", value = "/role/deconnexion")
public class deconnexionController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("auth")!= null) {
            request.getSession().removeAttribute("auth");
        }
        response.sendRedirect("view/connexion.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

