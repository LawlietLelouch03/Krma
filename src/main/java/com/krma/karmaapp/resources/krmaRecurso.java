/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krma.karmaapp.resources;

import com.krma.hibernateUtil.HibernateUtil;
import com.krma.karmaapp.models.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.hibernate.Session;

/**
 *
 * @author ericknavarro
 */
@Path("users")
public class krmaRecurso {
   
     @GET
     @Path("{id}")
     @Produces(MediaType.APPLICATION_JSON)
     public Response getUsersById(@PathParam("id") Integer id){
            Map<String, Object> response = new HashMap<>();
            
            int codigo = -1;
            String mensaje = "";
            int status = 0;
            Session session = null;
            List<User> lista = null;
            
            User user = null;
            
            try {
              // StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
             
             session = HibernateUtil.getSession();
             
            user = session.find(User.class, id);
            user.setPassword("");
             
             
            /*Query query = session.createQuery("FROM users U", User.class);
             lista = query.getResultList();*/
             
             codigo = Response.Status.OK.getStatusCode();
             
             mensaje = "Se obtuvieron los datos";
             
       
         } catch (Exception e) {
             e.printStackTrace();
             codigo = Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
             mensaje = e.getMessage();
             
         }  finally  {
                if(session != null) {
                    session.close();
                }
            }
            
            response.put("data", user);
            response.put("mensaje", mensaje);
            
            return Response.status(codigo).entity(response).build();
     }
    
    
    @GET 
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {
        Map<String, Object> response  = new HashMap<>();
        
        int codigo = -1;
        String mensaje= "";
        Session session = null;
        List<User> lista = null;
        
        try {
            session = HibernateUtil.getSession();
            
            Query query = session.createQuery("From users U", User.class);
            lista = query.getResultList();
            
            codigo = Response.Status.OK.getStatusCode();
            
            mensaje = "Se obtuvieron los datos";
            
            
        } catch (Exception e) {
            e.printStackTrace();
            codigo = Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
            mensaje = e.getMessage();
        } finally {
            if(session != null) {
                session.close();
            }
        }
        response.put("data", lista);
        response.put("mensaje", mensaje);
        
        return Response.status(codigo).entity(response).build();
        
   
     }
    
    
}
