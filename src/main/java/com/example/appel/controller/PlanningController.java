package com.example.appel.controller;

import com.example.appel.dao.EnseignantDAO;
import com.example.appel.dao.EtudiantDAO;
import com.example.appel.dao.SeanceDAO;
import com.example.appel.dao.UtilisateurDAO;
import com.example.appel.enumType.Role;
import com.example.appel.model.*;
import com.example.appel.util.DatePlanning;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet(name = "emploiController", value = "/role/emploi")
public class PlanningController extends HttpServlet {

    private static final SimpleDateFormat SDF = new SimpleDateFormat("dd-MM-yyyy");
    private static final SimpleDateFormat SDF_OLD_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Utilisateur utilisateur = (Utilisateur) request.getAttribute("utilisateur");
        Integer userId=(Integer) request.getSession().getAttribute("auth");
        EnseignantDAO enseignantDAO=new EnseignantDAO();
        Enseignant enseignant = enseignantDAO.find(userId);
        Date date = new Date();
        int dayOfWeek;
        long dayMonth;
        String strDate = request.getParameter("date");
        Calendar calendar = Calendar.getInstance();
        String planningAction = request.getParameter("planning_action");
        HashMap<Long, ArrayList<Seance>> seanceFilter = new HashMap<>();

        try {
            if (!(planningAction == null || planningAction.isEmpty()) && !(strDate == null || strDate.isEmpty())) {
                date = SDF_OLD_FORMAT.parse(strDate);
                calendar.setTime(date);
                switch (planningAction) {
                    case "previous" :
                        calendar.add(Calendar.WEEK_OF_MONTH, -1);
                        date = calendar.getTime();
                        break;
                    case "next" :
                        calendar.add(Calendar.WEEK_OF_MONTH, 1);
                        date = calendar.getTime();
                        break;
                }
            }
            calendar.setTime(SDF.parse(SDF.format(date)));
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            Date firstDayOfWeek = calendar.getTime();
            for (int i = 2; i <= 7; i++) {
                seanceFilter.put(calendar.getTimeInMillis(), new ArrayList<>());
                calendar.add(Calendar.DATE, 1);
            }
            Date lastDayOfWeek = calendar.getTime();

            Role role=(Role) request.getAttribute("role");
            List<Seance> seances=new ArrayList<>();
            if(role==Role.ENSEIGNANT){
                seances = enseignant.getSeances();
            }else if(role==Role.ETUDIANT){
                EtudiantDAO etudiantDAO=new EtudiantDAO();
                Etudiant etu=etudiantDAO.find(userId);
                seances = etudiantDAO.getSeances(etu.getId());
                //System.out.println("listeSeance:"+seances.size());
            }else if(role==Role.SCOLARITE){

            }

            for (Seance seance : seances) {
                if (DatePlanning.isWithinRange(seance.getDateDebut(), firstDayOfWeek, lastDayOfWeek)) {
                    calendar.setTime(seance.getDateDebut());
                    dayMonth = SDF.parse(SDF.format(seance.getDateDebut())).getTime();
                    if (seanceFilter.containsKey(dayMonth)) {
                        seanceFilter.get(dayMonth).add(seance);
                    } else {
                        seanceFilter.put(dayMonth, new ArrayList<>(Collections.singletonList(seance)));
                    }
                }
            }
            request.setAttribute("date", DatePlanning.getStrFormat(date, "yyyy-MM-dd"));
            request.setAttribute("seanceFilter", new TreeMap<>(seanceFilter));
            //request.setAttribute("page", "planning");
            if(role==Role.ENSEIGNANT){
                System.out.println("hello enseignant");
                request.getRequestDispatcher("/view/enseignant/accueilEns.jsp").forward(request,response);
            }else if(role==Role.ETUDIANT){
                request.setAttribute("today",new Date());
                request.getRequestDispatcher("/view/etudiant/accueilEtu.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        Role role=(Role) request.getAttribute("role");
//        if(role==Role.ENSEIGNANT){
//            request.getRequestDispatcher("/view/enseignant/accueilEns.jsp").forward(request, response);
//        }else if(role==Role.ETUDIANT){
//            request.getRequestDispatcher("/view/etudiant/accueilEtu.jsp").forward(request, response);}
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
