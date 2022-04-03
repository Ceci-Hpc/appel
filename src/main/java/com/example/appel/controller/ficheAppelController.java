package com.example.appel.controller;

import com.example.appel.dao.*;
import com.example.appel.enumType.EtatAppel;
import com.example.appel.model.Etat;
import com.example.appel.model.Etudiant;
import com.example.appel.model.FicheAppel;
import com.example.appel.model.Seance;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.ParseException;
import java.util.*;

@WebServlet(name = "appelController", value = "/role/appel")
public class ficheAppelController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer seanceId;

        if (req.getSession().getAttribute("seance") == null) {
            seanceId = Integer.valueOf(req.getParameter("id"));
        } else {
            Seance seance = (Seance) req.getSession().getAttribute("seance");
            seanceId = seance.getId();
        }
        SeanceDAO seanceDAO = new SeanceDAO();
        EtudiantDAO etudiantDAO = new EtudiantDAO();
        Seance seance = seanceDAO.find(seanceId);

        List<Etudiant> listEtudiant = new ArrayList<>();
        listEtudiant = seanceDAO.getAllEtudiants(seanceId);//obtenir tous les etudiants de la seance
        req.setAttribute("seance", seance);
        req.getSession().setAttribute("seanceSession", seance);
        req.setAttribute("listEtudiant", listEtudiant);
        req.getSession().setAttribute("listAllEtudiant", listEtudiant);

//        List<Etudiant>idEtu = new ArrayList<>();
//        for (Etudiant etudiant : listEtudiant) {
//            idEtu.add(etudiantDAO.find(etudiant.getId()));
//        }
//        System.out.println("idetu: "+idEtu);
//        req.setAttribute("idEtu",idEtu);
        req.setAttribute("nbEtudiant", listEtudiant.size());

        //prevenir absence
        JustificatifDAO justificatifDAO = new JustificatifDAO();
        List<Boolean> prevu = new ArrayList<>();
        for(Etudiant e:listEtudiant){
            prevu.add(justificatifDAO.existJustifPrevu(seanceId,e.getId()));
            System.out.println("prevu is :"+justificatifDAO.existJustifPrevu(seanceId,e.getId()));
        }
//        try {
//          prevu =justificatifDAO.existJustifPrevu(seanceId, listEtudiant);
//         } catch (ParseException e) {
//            e.printStackTrace();
//        }
        req.setAttribute("prevu",prevu);

        FicheAppelDAO ficheAppelDAO = new FicheAppelDAO();
        FicheAppel f = null;
        f = ficheAppelDAO.getFicheAppel(seanceId);
        req.setAttribute("isEnrg", f.isEnregistre());
        System.out.println("enregistre: "+f.isEnregistre());
        req.setAttribute("isValide", f.isValide());
        List<Etat> etats = new ArrayList<>();
        EtatDAO etatDAO = new EtatDAO();
        if (f.isEnregistre() == true) {
            for (int i = 0; i < listEtudiant.size(); i++) {
                etats.add(etatDAO.find(listEtudiant.get(i).getId(), f.getId()));
            }
        }
        req.setAttribute("etats", etats);
        System.out.println("etats size: "+etats.size());
        HashMap<Etudiant,String[]> notification = new HashMap<>();

        for (Etudiant e : listEtudiant) {
            int absence= etudiantDAO.absencesEtuCours(e.getId(),seance.getCours().getNomCours()).size();
            int retard = etudiantDAO.retardsEtuCours(e.getId(),seance.getCours().getNomCours()).size();
            String nbAbsence = "";
            String nbRetard = "";
            if (absence == 0 && retard!= 0){
                nbRetard = "Retard : " + retard;
            }else if (retard == 0 && absence!= 0){
                nbAbsence = "Absence : "+ absence;
            }else if (retard == 0 && absence == 0){
                nbAbsence = "";
                nbRetard = "";
            }else {
                nbAbsence = "Absence : "+ absence;
                nbRetard = "Retard : " + retard;
            }
            notification.put(e,new String[]{nbAbsence,nbRetard});
        }

        req.setAttribute("notification",notification);
        //System.out.println("etats"+req.getAttribute("etats"));

        req.getRequestDispatcher("/view/enseignant/appel.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //HashMap<Integer,String> etats=new HashMap<>();
        //Integer nbEtudiant=(Integer) req.getAttribute("nbEtudiant");
        List<Etudiant> listEtudiant = new ArrayList<>();
        listEtudiant = (List<Etudiant>) req.getSession().getAttribute("listAllEtudiant");
        Integer nbEtudiant = listEtudiant.size();
        SeanceDAO seanceDAO = new SeanceDAO();//test
        //req.setAttribute("seance",seanceDAO.find(1));//test
        Seance seance = (Seance) req.getSession().getAttribute("seanceSession");
        EtatDAO etatDAO = new EtatDAO();
        FicheAppelDAO ficheAppelDAO = new FicheAppelDAO();
        FicheAppel ficheAppel = null;
        ficheAppel = ficheAppelDAO.getFicheAppel(seance.getId());
        String enrg = req.getParameter("enregistrer");
        String valide = req.getParameter("valider");
        String imprimer=req.getParameter("imprimer");
        String ajouter = req.getParameter("ajouter");
        String supprimer = req.getParameter("supprimer");
        String email = req.getParameter("email");
        String eg = "enregistrer";
        String vd = "valider";
        String imp="imprimer";
        String aj = "ajouter";
        String sp = "supprimer";
        System.out.println(enrg);

        if (eg.equals(enrg) || (vd.equals(valide) && ficheAppel.isEnregistre() == false)) {
            for (int i = 0; i < nbEtudiant; i++) {
                System.out.println("test1");
                Etat et = new Etat();
                et.setEtudiant(listEtudiant.get(i));
                et.setFicheAppel(ficheAppel);
                if (req.getParameter(String.valueOf(listEtudiant.get(i).getId())).equals("0")) {
                    et.setEtatAppel(EtatAppel.PRESENCE);
                } else if (req.getParameter(String.valueOf(listEtudiant.get(i).getId())).equals("1")) {
                    et.setEtatAppel(EtatAppel.RETART);
                } else if (req.getParameter(String.valueOf(listEtudiant.get(i).getId())).equals("2")) {
                    et.setEtatAppel(EtatAppel.ABSENCE);
                }
                if (ficheAppel.isEnregistre() == false) {
                    //System.out.println("isenregistrer non");
                    etatDAO.create(et);
                } else if (ficheAppel.isEnregistre() == true) {
                    System.out.println("isenregistrwr yes");
                    System.out.println("fiche:" + (ficheAppel.isEnregistre() == true));
                    Etat etReEnrg = etatDAO.find(listEtudiant.get(i).getId(), ficheAppel.getId());
                    etReEnrg.setEtatAppel(et.getEtatAppel());
                    etatDAO.update(etReEnrg);
                }
            }
            if (vd.equals(valide)) {
                ficheAppel.setValide(true);
            }
            ficheAppel.setEnregistre(true);
            ficheAppelDAO.update(ficheAppel);
        } else if (vd.equals(valide)) {
            ficheAppel.setValide(true);
            ficheAppelDAO.update(ficheAppel);
        } else {
            System.out.println("down");
        }

        UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
        EtudiantDAO etudiantDAO = new EtudiantDAO();
        Etudiant etu = null;
        System.out.println("mon ajouter" + ajouter);
        System.out.println("ajouter:" + aj.equals(ajouter));
        System.out.println("mon supprimer" + supprimer);
        System.out.println("supprimer" + sp.equals(supprimer));
        if (aj.equals(ajouter)) {
            System.out.println("ajouter");
            etu = etudiantDAO.find(utilisateurDAO.findByEmail(email).getId());
            Etat etNewEtu = new Etat();
            etNewEtu.setEtatAppel(EtatAppel.PRESENCE);
            etNewEtu.setFicheAppel(ficheAppel);
            etNewEtu.setEtudiant(etu);
            etatDAO.create(etNewEtu);
        } else if (sp.equals(supprimer)) {
            System.out.println("supprimer");
            etu = etudiantDAO.find(utilisateurDAO.findByEmail(email).getId());
            Etat etModEtu = etatDAO.find(etu.getId(), ficheAppel.getId());
            etatDAO.delete(etModEtu);
        }

        if(imp.equals(imprimer)){
            System.out.println("fiche appel Id: "+ficheAppel.getId());
            getExcel(ficheAppel.getId(),resp);
        }
        resp.sendRedirect("/role");
    }
    private void getExcel(Integer ficheAppelId,HttpServletResponse response) throws IOException {

        Workbook wb=new HSSFWorkbook();

        Sheet sheet = wb.createSheet();

        List<Etat> etats=new ArrayList<>();
        EtatDAO etatDAO=new EtatDAO();
        etats=etatDAO.getEtats(ficheAppelId);

        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("Nom");
        row.createCell(1).setCellValue("Prenom");
        row.createCell(2).setCellValue("Email");
        row.createCell(3).setCellValue("Etat");

        FicheAppelDAO ficheAppelDAO=new FicheAppelDAO();
        FicheAppel ficheAppel=ficheAppelDAO.find(ficheAppelId);

        for(int i=0;i<etats.size();i++){

            Row row1 = sheet.createRow(i + 1);
            row1.createCell(0).setCellValue(etats.get(i).getEtudiant().getNom());
            row1.createCell(1).setCellValue(etats.get(i).getEtudiant().getPrenom());
            row1.createCell(2).setCellValue(etats.get(i).getEtudiant().getEmail());
            row1.createCell(3).setCellValue(etats.get(i).getEtatAppel().toString());
        }

        String filename=ficheAppel.getSeance().getCours().getNomCours()+""+ficheAppel.getSeance().getNumSeance()+"_appel.xls";
        response.setContentType("application/msexcel");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-disposition", "attachment; filename=" + filename);
//        String realPath = getServletContext().getRealPath(ficheAppel.getSeance().getCours().getNomCours()+""+ficheAppel.getSeance().getNumSeance()+"_appel.xls");
        //System.out.println("realPaht:"+realPath);
        File file = new File(filename);
        FileOutputStream fos=new FileOutputStream(file);
        wb.write(fos);
        BufferedInputStream bis=new BufferedInputStream(new FileInputStream(file));
        ServletOutputStream outputStream = response.getOutputStream();
        byte[] bytes=new byte[1024];
        int len=0;
        while ((len=bis.read(bytes))!=-1){
            outputStream.write(bytes,0,len);
        }
        outputStream.flush();
        outputStream.close();

    }
}
