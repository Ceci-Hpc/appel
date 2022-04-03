package com.example.appel.filter;


import com.example.appel.dao.UtilisateurDAO;
import com.example.appel.enumType.Role;
import com.example.appel.model.Enseignant;
import com.example.appel.model.Etudiant;
import com.example.appel.model.Scolarite;
import com.example.appel.model.Utilisateur;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "roleFilter", urlPatterns = "/role/*")
public class roleFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Integer userID = (Integer) ((HttpServletRequest) servletRequest).getSession().getAttribute("auth");
        if (userID == null) {
            ((HttpServletResponse) servletResponse).sendRedirect("/connexion");
        } else {
            UtilisateurDAO utilisateurDao = new UtilisateurDAO();
            Utilisateur utilisateur = utilisateurDao.find(userID);
            servletRequest.setAttribute("utilisateur", utilisateur);
            Role role = null;
            if (utilisateur instanceof Enseignant) {
                role = Role.ENSEIGNANT;
            } else if (utilisateur instanceof Scolarite) {
                role = Role.SCOLARITE;
            } else if (utilisateur instanceof Etudiant) {
                role = Role.ETUDIANT;
            }
            servletRequest.setAttribute("role", role);
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
