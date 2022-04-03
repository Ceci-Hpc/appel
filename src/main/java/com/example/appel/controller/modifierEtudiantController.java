package com.example.appel.controller;

import com.example.appel.config.HibernateUtil;
import com.example.appel.dao.EtudiantDAO;
import com.example.appel.dao.FormationDAO;
import com.example.appel.dao.ScolariteDAO;
import com.example.appel.enumType.GroupEtudiant;
import com.example.appel.enumType.TypeEtudiant;
import com.example.appel.model.Etudiant;
import com.example.appel.model.Utilisateur;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "modifierEtudiantController", value = "/role/modifierEtudiant")
public class modifierEtudiantController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Etudiant>etudiants = new ArrayList<Etudiant>();
        ScolariteDAO scolariteDAO = new ScolariteDAO();
        try {
            etudiants = ScolariteDAO.getListEtu();
            req.setAttribute("listEtu",etudiants);
            req.getSession().setAttribute("listEtudiant",etudiants);

        } catch (HibernateException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("/view/scolarite/modifierEtu.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Etudiant e=null;

        List<Etudiant> listEtu=(List<Etudiant>)req.getSession().getAttribute("listEtudiant");
        //System.out.println("length:"+listEtu.size());
        String vd="valider";
        for(int i=0;i<listEtu.size();i++){
            if(vd.equals(req.getParameter(""+listEtu.get(i).getId()))){
                String formation = req.getParameter(listEtu.get(i).getId()+"formation");
                String type=req.getParameter(listEtu.get(i).getId()+"type");
                String groupe = req.getParameter(listEtu.get(i).getId()+"group");
                //System.out.println("my group is :"+groupe);
                Integer id = Integer.valueOf(req.getParameter(listEtu.get(i).getId()+"id"));
                //System.out.println("etudiantId:"+id);
                EtudiantDAO etudiantDAO = new EtudiantDAO();

                e=etudiantDAO.find(id);
                FormationDAO formationDAO=new FormationDAO();
                e.setFormation(formationDAO.findByName(formation));
                e.setTypeEtudiant(TypeEtudiant.valueOf(type));
                e.setGroupEtudiant(GroupEtudiant.valueOf(groupe));
                etudiantDAO.updateEtudiant(e);
            }
        }

        req.getRequestDispatcher("/view/scolarite/acceuilSco.jsp").forward(req,resp);

    }
}
