package com.example.appel.controller;

import com.example.appel.dao.*;
import com.example.appel.model.*;
import org.apache.commons.fileupload.FileItem;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "justifController", value = "/role/justificatifEtu")
public class justifEtuController extends HttpServlet {
    public CoursDAO coursDAO=new CoursDAO();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Cours> listCours= new ArrayList<>();
        Utilisateur u=(Utilisateur) req.getAttribute("utilisateur");
        listCours=coursDAO.getCoursEtudiant(u.getId());
        req.setAttribute("listCours",listCours);
        req.getRequestDispatcher("/view/etudiant/justificatifEtu.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String titre=req.getParameter("titre");
//        System.out.println("titre:"+titre);
        Integer seanceId=Integer.valueOf(req.getParameter("id"));

        Date currentDate=new Date();

        EtudiantDAO etudiantDAO=new EtudiantDAO();
        Utilisateur u=(Utilisateur) req.getAttribute("utilisateur");
        Etudiant e=etudiantDAO.find(u.getId());
        boolean validee = false;
        boolean traite = false;

        SeanceDAO seanceDAO=new SeanceDAO();
        Seance seance=seanceDAO.find(seanceId);

        List<String> list=new ArrayList<String>();
        DocumentDAO documentDAO=new DocumentDAO();
        String filename=documentDAO.getFileNewName();
        ServletContext servletContext=null;
        servletContext=req.getSession().getServletContext();

        List<FileItem> items=DocumentDAO.getRequsetFileItems(req,servletContext);
        //boolean isLoadToSQL=false;
        for(FileItem item:items) {
            if(!item.isFormField()){
                System.out.println("item is "+item);
                DocumentDAO.saveFile(item,filename);
            }else {
                list.add(item.getString("UTF-8"));
            }
        }
        //String finalFileName= "/document/"+filename;
        //System.out.println("titre"+list.get(0));
        JustificatifDAO j=new JustificatifDAO();
        j.create(new Justificatif(list.get(0),currentDate,validee,traite,e,seance,filename));
        //req.getRequestDispatcher("/view/etudiant/accueilEtu.jsp").forward(req, resp);
        resp.sendRedirect("/role");
    }
}
