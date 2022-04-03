package com.example.appel.controller;



import com.example.appel.dao.EtudiantDAO;
import com.example.appel.enumType.TypeEtudiant;
import com.example.appel.model.Etat;
import com.example.appel.model.Etudiant;
import com.example.appel.model.Utilisateur;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ConsulterAbsEtuController", value = "/role/consulterAbsEtu")
public class consulterAbsEtuController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Utilisateur utilisateur = (Utilisateur) request.getAttribute("utilisateur");
        EtudiantDAO etudiantDAO = new EtudiantDAO();

        List<Etat> absences = etudiantDAO.getAbsencesSeanceTotal(utilisateur.getId());
        //List<Etat> absences = etudiantDAO.getAbsencesSeanceTotal(8);
        request.setAttribute("listAbsencesEtu", absences);


        //request.setAttribute("page","consulterAbsence");
        request.getRequestDispatcher("/view/etudiant/consulterAbsEtu.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EtudiantDAO etudiantDAO = new EtudiantDAO();
        Integer utilisateurId=(Integer) req.getSession().getAttribute("auth");
        //String utilisateurS = req.getParameter("utilisateur");
        //request.getSession().setAttribute("user", user);
        //Utilisateur utilisateur = (Utilisateur) req.getParameter("utilisateur");
        int mois = Integer.parseInt((String) req.getParameter("mois"));
        System.out.print(utilisateurId);
        System.out.print(mois);

            if (mois == 0) {
                List<Etat> absences1 = etudiantDAO.getAbsencesSeanceTotal(utilisateurId);
                System.out.println(absences1.size());
                //List<Etat> absences = etudiantDAO.getAbsencesSeanceTotal(8);
                req.setAttribute("listAbsencesEtu", absences1);
                for (Etat e : absences1){
                    System.out.println(e.getId());
                }
//                req.getRequestDispatcher("/view/etudiant/consulterAbsEtu.jsp").forward(req, resp);
            } else {

                List<Etat> absencesParMois1 = etudiantDAO.getAbsencesSeanceParMois(utilisateurId,mois);
                System.out.println(absencesParMois1.size());
                req.setAttribute("listAbsencesEtu", absencesParMois1);
                for (Etat e : absencesParMois1){
                    System.out.println(e.getId());
                }

            }
            req.getRequestDispatcher("/view/etudiant/consulterAbsEtu.jsp").forward(req, resp);
        }



}
