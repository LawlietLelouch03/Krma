/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krma.karmaapp.resources;

import com.krma.hibernateUtil.HibernateUtil;
import com.krma.karmaapp.models.Comment;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.hibernate.Session;

/**
 *
 * @author soporte
 */
@Path("comments")
public class CommentRecurso {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getComments(){
        
        int status = 0;
        String mensaje = null;
        Session session = null;
        List<Comment> comments = null;
    
        Map<String, Object> response = new HashMap<>();
    
        try {
            
            session = HibernateUtil.getSession();
            
            Query query = session.createQuery("FROM comments ", Comment.class);
            
            comments = query.getResultList();
            
            status = Response.Status.OK.getStatusCode();
            
            mensaje = "Se obtuvieron los comentarios";
            
        } catch (Exception e) {
            status = Response.Status.OK.getStatusCode();
            mensaje = e.getMessage();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        
        response.put("data", comments);
        response.put("mensaje", mensaje);
        
        return Response.status(status).entity(response).build();
    }
    
}
