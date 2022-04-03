package com.example.appel.controller;

import com.example.appel.dao.*;
import com.example.appel.enumType.Role;
import com.example.appel.enumType.TypeEtudiant;
import com.example.appel.model.Etudiant;
import com.example.appel.model.Utilisateur;
import org.apache.commons.fileupload.FileItem;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "profileEtuController", value = "/role/profileEtu")
public class profileEtuController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UtilisateurDAO utilisateurDAO=new UtilisateurDAO();
        Utilisateur u=utilisateurDAO.find((Integer) req.getSession().getAttribute("auth"));
        req.setAttribute("utilisateur",u);
        System.out.println("hello"+req.getAttribute("utilisateur"));
        if(req.getAttribute("role")==Role.ETUDIANT){
            EtudiantDAO etudiantDAO=new EtudiantDAO();
            Etudiant e=etudiantDAO.find(u.getId());
            req.setAttribute("nomFormation",e.getFormation().getNomFormation());
            System.out.println("nomFormation:"+req.getAttribute("nomFormation"));
            req.setAttribute("nomType",e.getTypeEtudiant().toString());
            System.out.println("nomType:"+req.getAttribute("nomType"));
        }
        req.setAttribute("photo",u.getPhoto());
        req.getRequestDispatcher("/view/page/profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Etudiant e=null;
        HashMap<String,String> erreurs=new HashMap<>();

        List<String> list=new ArrayList<String>();
        PhotoDAO photoDAO=new PhotoDAO();
        ServletContext servletContext=null;
        String filename=null;
        servletContext=req.getSession().getServletContext();
        List<FileItem> items=photoDAO.getRequsetFileItems(req,servletContext);
        //boolean isLoadToSQL=false;
        for(FileItem item:items) {
            if(!item.isFormField()&&item.getSize()>0){
                System.out.println("item is"+item);
                filename=photoDAO.getPhotoNewName();
                photoDAO.saveFile(item,filename);
            }else {
                list.add(item.getString("UTF-8"));
            }
        }
//        String finalFileName= "/appel/image/"+filename;
        String prenom=list.get(0);
        String nom=list.get(1);
        System.out.println("paramater nom:"+nom);
        String email=list.get(2);
        String phone=list.get(3);

        if (prenom == null || prenom.isEmpty()) {
            System.out.println(1);
            erreurs.put("prenom_erreur", "Veillez entrer le prenom");
        }
        if (nom == null || nom.isEmpty()) {
            System.out.println(2);
            erreurs.put("nom_erreur", "Veillez entrer le nom");
        }
        if (email == null || email.isEmpty()) {
            System.out.println(3);
            erreurs.put("email_erreur", "Veillez entrer l'addresse email");
        }
        if (phone == null || phone.isEmpty()) {
            System.out.println(4);
            erreurs.put("phone_erreur", "Veillez entrer le phone number");
        }

        EtudiantDAO etudiantDAO=new EtudiantDAO();
        System.out.println("erreurs="+erreurs.isEmpty());
        Utilisateur u=(Utilisateur) req.getAttribute("utilisateur");
        if (erreurs.isEmpty()) {
            try {
                if(req.getAttribute("role")==Role.ETUDIANT){
                    e=etudiantDAO.find(u.getId());
                    e.setNom(nom);
                    e.setPrenom(prenom);
                    e.setEmail(email);
                    e.setPhone(phone);
                    if(filename!=null){
                        e.setPhoto(filename);
                    }
                    FormationDAO formationDAO=new FormationDAO();
                    e.setFormation(formationDAO.findByName(list.get(4)));
                    e.setTypeEtudiant(TypeEtudiant.valueOf(list.get(5)));
                    etudiantDAO.updateEtudiant(e);
                    System.out.println("update nom:"+e.getNom());
                }else{
                    u.setNom(nom);
                    u.setPrenom(prenom);
                    u.setEmail(email);
                    u.setPhone(phone);
                    if(filename!=null){
                        u.setPhoto(filename);
                    }
                    UtilisateurDAO utilisateurDAO=new UtilisateurDAO();
                    utilisateurDAO.update(u);
                }
                resp.sendRedirect("/role");
            } catch (Exception ex) {
                ex.printStackTrace();
                req.setAttribute("generale_erreur", "Erreur technique");
                //req.getRequestDispatcher("/view/etudiant/accueilEtu.jsp").forward(req, resp);
                resp.sendRedirect("/role");
            }

        } else {
            erreurs.forEach(req::setAttribute);
            //req.getRequestDispatcher("/view/etudiant/accueilEtu.jsp").forward(req, resp);
            resp.sendRedirect("/role");
        }
    }
}
