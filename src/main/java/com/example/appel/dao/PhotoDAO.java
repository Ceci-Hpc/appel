package com.example.appel.dao;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.List;

public class PhotoDAO {

    public static List<FileItem> getRequsetFileItems(HttpServletRequest request, ServletContext servletContext){
        boolean isMultipart= ServletFileUpload.isMultipartContent(request);
        if(isMultipart) {
            System.out.println("ismul");
            DiskFileItemFactory factory = new DiskFileItemFactory();
            String str="javax.servlet.context.tempdir";
            File repository=(File) servletContext.getAttribute(str);
            factory.setRepository(repository);
            ServletFileUpload upload=new ServletFileUpload(factory);
            try {
                System.out.println("items");
                return upload.parseRequest(request);
            }catch (FileUploadException e) {
                System.out.println("aucune image obtenu");
                return null;
            }
        }else {
            return null;
        }
    }

    public static boolean saveFile(FileItem item,String fileName) {
        File savePath=new File("C:\\Users\\acyeol\\Desktop\\appelPC\\src\\main\\webapp\\images"); //Remplacer par votre propre chemin de stockage
        if(!savePath.exists()) {
            savePath.mkdirs();
        }
        File uploadFile=new File(savePath+File.separator+fileName);
        try{
            item.write(uploadFile);
            System.out.println("Enregistrement reussi");
            return true;
        }catch(Exception e){
            System.out.println("Echec");
        }
        return false;
    }

    public static int getSecondTimestamp(Date date){
        if (null == date) {
            return 0;
        }
        String timestamp = String.valueOf(date.getTime());
        System.out.println(timestamp);
        int length = timestamp.length();
        if (length > 3) {
            return Integer.valueOf(timestamp.substring(0,length-3));
        } else {
            return 0;
        }
    }

    /**
     *
     *obtenir un nouveau nom de l'image
     */
    public static String getPhotoNewName() {
        Date date=new Date();
        int second=getSecondTimestamp(date);
        String fileName=String.valueOf(second)+".jpg";
        return fileName;
    }

}
