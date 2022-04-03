package com.example.appel.controller;

//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
import com.example.appel.dao.EtatDAO;
import com.example.appel.dao.JustificatifDAO;
import com.example.appel.dao.UtilisateurDAO;
import com.example.appel.model.Etat;
import com.example.appel.model.Justificatif;
import com.example.appel.model.Utilisateur;
import com.example.appel.util.SendEmailUtil;
//import lombok.extern.slf4j.Slf4j;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

//@Slf4j
//@RestController
//@Controller
//@RequestMapping
@WebServlet(name = "emailController", value = "/api/front/email/sendAbsence")
public class EmailController  extends HttpServlet {


    //@Autowired
    EtatDAO etatDAO = new EtatDAO();
    //@Autowired
    UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
    //@Autowired
    JustificatifDAO justificatifDAO = new JustificatifDAO();

    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
//        super.doPost(req, response);
        System.out.println("post");
        String ids = req.getParameter("ids");
        String type = req.getParameter("type");

        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type", "application/json");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Max-Age", "3600");
//        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json;charset=UTF-8");
//        ServletOutputStream out = null;
        PrintWriter writer=null;
        try {
            // JSON.toJSONString(result)
//            response.getWriter().write(JSON.toJSONString(result));
            writer=response.getWriter();
//            writer.write(result.toString());

            if("0".equals(type)){
                writer.write(new Gson().toJson(sendAbsence(ids.split(","),"Absence à justifier","Bonjour," +
                        "Vous avez une absence pour un cours que vous vous inscrit" +
                        "pour justifier cette absence veuillez cliquer sur la séance correspondante dans votre emploi du temps"
                )));
            }else{
                writer.write(new Gson().toJson(sendAbsence(ids.split(","),"Justificatif validé","Bonjour, " +
                        "Votre justificatif d'absence a été récentement validé par la scolarité")));
            }

            writer.flush();
        } catch (IOException ex) {
            //log.error(ex.getMessage());
            ex.printStackTrace();
        }finally {
            if(writer!=null) {
                writer.close();
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(req, response);
    }


    public Map sendAbsence(String[] ids, String title, String body){
        Map rsp  =new HashMap();
        rsp.put("code","500");

        try {
            if(Objects.isNull(ids)){
                rsp.put("msg","Rien à envoyer");
                return rsp;
            }
            int isSend = 0;
            for ( String id: ids) {
                Utilisateur utilisateur = utilisateurDAO.find(Integer.valueOf(id));
                if(Objects.nonNull(utilisateur) || StringUtils.isNotEmpty(utilisateur.getEmail())){
                    try {
                        new SendEmailUtil(utilisateur.getEmail(), title, body).run();
                        isSend++;
                    }catch (Exception e){
                        e.printStackTrace();
                        //log.error(e.getMessage());
                    }
                }

            }
            rsp.put("code","200");
            rsp.put("msg",String.format(" En total %s étudiants，envoyé %s alertes",ids.length,isSend));
            return rsp;
        }catch (Exception e){
            e.printStackTrace();
        }
        rsp.put("msg","Erreur");
        return  rsp;

    }

}

