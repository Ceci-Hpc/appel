package com.example.appel.controller;

import com.example.appel.dao.CoursDAO;
import com.example.appel.dao.EtudiantDAO;
import com.example.appel.dao.SeanceDAO;
import com.example.appel.model.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "BilanCoursController", value = "/role/consulterCours")
public class bilanCoursController extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Utilisateur utilisateur = (Utilisateur) request.getAttribute("utilisateur");
        //Integer coursId = (Integer) request.getAttribute("coursId");


        float nbAbsenceMoyenne = 0;
        CoursDAO coursDAO = new CoursDAO();
        List<Cours> courslist = new ArrayList<>();
        courslist = coursDAO.getAllCours();
        for (Cours c : courslist){
            System.out.println(c.getId());
        }
        Integer absenceTotal=coursDAO.getCoursAbsenceTotal();
        Integer nombreSeance = coursDAO.getNombreSeance();
        nbAbsenceMoyenne =(float) absenceTotal/nombreSeance;
        request.setAttribute("coursList",courslist);
        request.setAttribute("total",absenceTotal);
        request.setAttribute("Moyenne", nbAbsenceMoyenne);

        List<Etudiant> etudiants = new ArrayList<>();
        EtudiantDAO etudiantDAO = new EtudiantDAO();
        etudiants = etudiantDAO.getEtudiantAbsenceSup3();
        HashMap<Etudiant, Integer> nbabsence = new HashMap<>();

        for (Etudiant e : etudiants ){
            nbabsence.put(e,etudiantDAO.getNbAbsenceEtu(e.getId()));
        }
        request.setAttribute("nbabsence", nbabsence);

        request.getRequestDispatcher("/view/enseignant/bilanCours.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CoursDAO coursDAO = new CoursDAO();
        List<Cours> courslist = new ArrayList<>();
        courslist = coursDAO.getAllCours();
        req.setAttribute("coursList",courslist);

        //Utilisateur utilisateur = (Utilisateur) request.getAttribute("utilisateur");
        Integer coursId = Integer.valueOf(req.getParameter("cours"));
        System.out.println(coursId);
        if (coursId == 0) {
            float nbAbsenceMoyenne = 0;
            //CoursDAO coursDAO = new CoursDAO();
            Integer absenceTotal=coursDAO.getCoursAbsenceTotal();
            Integer nombreSeance = coursDAO.getNombreSeance();
            nbAbsenceMoyenne =(float) absenceTotal/nombreSeance;
            req.setAttribute("total",absenceTotal);
            req.setAttribute("Moyenne", nbAbsenceMoyenne);

            List<Etudiant> etudiants = new ArrayList<>();
            EtudiantDAO etudiantDAO = new EtudiantDAO();
            etudiants = etudiantDAO.getEtudiantAbsenceSup3();
            HashMap<Etudiant, Integer> nbabsence = new HashMap<>();

            for (Etudiant e : etudiants ){
                nbabsence.put(e,etudiantDAO.getNbAbsenceEtu(e.getId()));
            }
            req.setAttribute("nbabsence", nbabsence);



            req.getRequestDispatcher("/view/enseignant/bilanCours.jsp").forward(req, resp);
        }
        else {
            float nbAbsenceMoyenne = 0;
            //CoursDAO coursDAO = new CoursDAO();
            Integer absenceTotal = coursDAO.getCoursAbsenceTotalParCours(coursId);
            Integer nombreSeance = coursDAO.getNombreSeanceParCours(coursId);
            nbAbsenceMoyenne = (float) absenceTotal / nombreSeance;
            req.setAttribute("total", absenceTotal);
            req.setAttribute("Moyenne", nbAbsenceMoyenne);

            List<Etudiant> etudiants = new ArrayList<>();

            EtudiantDAO etudiantDAO = new EtudiantDAO();
            CoursDAO coursDAO1 = new CoursDAO();
            etudiants = coursDAO1.getAbsencesUp3(coursId);
            HashMap<Etudiant, Integer> nbabsence = new HashMap<>();

            for (Etudiant e : etudiants) {
                nbabsence.put(e, etudiantDAO.getNbAbsenceParCoursEtu(e.getId(), coursId));
            }
            req.setAttribute("nbabsence", nbabsence);

            List<Seance> seances = coursDAO.getSeanceCours(coursId);
            for (Seance s : seances){
                System.out.println(s.getNumSeance());
            }

            HashMap<Integer, Float> tauxAbsenceList = new HashMap<Integer, Float>();
            List<Etudiant> listEtudiant=new ArrayList<>();
            SeanceDAO seanceDAO = new SeanceDAO();


            for (Seance s : seances) {
                Integer absence = coursDAO.getCoursAbsenceParSeance(coursId, s.getNumSeance());
                System.out.println(absence);
                listEtudiant=seanceDAO.getAllEtudiantsParseance(coursId, s.getNumSeance());
                //Integer doitpresence = coursDAO.getCoursPresenceParSeance(coursId, s.getNumSeance());
                System.out.println(listEtudiant.size());
                if (listEtudiant.size()!=0){
                float tauxAbsence = (float) absence / (listEtudiant.size());

                tauxAbsenceList.put(s.getNumSeance(), tauxAbsence);
                }

            }
            for (Integer i : tauxAbsenceList.keySet()){
                System.out.println("key: " +i +  "valeur" +tauxAbsenceList.get(i));
            }



            req.setAttribute("tauxAbsenceList", tauxAbsenceList);

            req.getRequestDispatcher("/view/enseignant/bilanCours.jsp").forward(req, resp);
        }
    }


}
