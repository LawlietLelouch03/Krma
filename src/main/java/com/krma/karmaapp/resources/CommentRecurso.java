/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krma.karmaapp.resources;

import com.krma.hibernateUtil.HibernateUtil;
import com.krma.karmaapp.models.Comment;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;

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
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createComment(Comment comment){
    
        Session session = null;
        Transaction tx = null;

        Map<String, Object> response = null;

        int codigo = Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
        
        int codigoInt = 0;
        String mensaje = null;
        try {

            session = HibernateUtil.getSession();
            
            tx = session.beginTransaction();
            
            comment.setFecha(new Date()); 
            session.save(comment); 
            tx.commit();
           
            codigo = Response.Status.CREATED.getStatusCode();
            codigoInt = 1; 
            mensaje = "Se creó el comentario";

        } catch (TransactionException e) {
            if (tx != null) {
                tx.rollback(); // deshacer la operacion con la bd
            }
            mensaje = "Fallo en la transacción";
        } catch (HibernateException e) {
            e.printStackTrace();
            mensaje = "Error en el servidor";
        } catch (Exception e) {
            e.printStackTrace();
            mensaje = "Error";
        } finally {
            if (session != null) {
                HibernateUtil.closeSession(session);
            }
        }

        response = new HashMap<>();
        response.put("codigo", codigoInt);
        response.put("mensaje", mensaje);
        response.put("data", comment);

        return Response.status(codigo).entity(response).build();
    
    }
    
    
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateComment(@PathParam("id") Integer id, Comment comment){
    
        Session session = null;
        Transaction tx = null;
        Map<String, Object> response = null;

        int status = Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
        int codigoInt = 0;
        String mensaje = null;

        Comment commentUpdate = null;

        try {
            session = HibernateUtil.getSession();

            commentUpdate = session.find(Comment.class, id);

            if (commentUpdate != null) {
                tx = session.beginTransaction();
                
                commentUpdate.setContenido(comment.getContenido());

                session.update(commentUpdate);
                tx.commit();

                status = Response.Status.OK.getStatusCode();
                mensaje = "Se actualizo el recurso";
                codigoInt = 1;
            } else {
                status = Response.Status.NOT_FOUND.getStatusCode();
                mensaje = "Recurso no encontrado";

            }

        } catch (TransactionException e) {
            e.printStackTrace();
            mensaje = "Error en la transaccion";
        } catch (HibernateException e) {
            e.printStackTrace();
            mensaje = "Error en la base de datos";
        } catch (Exception e) {
            e.printStackTrace();
            mensaje = "Error, consulta mas tarde";
        } finally {
            if (session != null) {
                HibernateUtil.closeSession(session);

            }
        }
        response = new HashMap<>();
        response.put("codigo", codigoInt);
        response.put("mensaje", mensaje);
        response.put("data", commentUpdate);

        // Construir la respuesta
        return Response.status(status).entity(response).build();
    
    }
    
    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteComment(@PathParam("id") Integer id){
        
        Session session = null;
        // ACID
        Transaction tx = null;
        Comment comment = null;

        Map<String, Object> response = null;
        int status = Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
        int codigo = 0;
        String mensaje = null;

        try {

            session = HibernateUtil.getSession();
            // Llevar de tarea get, load, find
            comment = session.get(Comment.class, id);
            if (comment != null) {
                tx = session.beginTransaction();
                session.remove(comment);
                tx.commit();

                mensaje = "Recurso eliminado";
                status = Response.Status.OK.getStatusCode();
            } else {
                status = Response.Status.NOT_FOUND.getStatusCode();
                mensaje = "Recurso no encontado";
            }
        } catch (TransactionException e) {
            e.printStackTrace();
            mensaje = "Error en la transacción";
        } catch (HibernateException e) {
            e.printStackTrace();
            mensaje = "Error con la base de datos";
        } catch (Exception e) {
            e.printStackTrace();
            mensaje = "Error, consulta mas tarde";
        } finally {
            if (session != null) {
                HibernateUtil.closeSession(session);;
            }
        }

        response = new HashMap<>();
        response.put("codigo", codigo);
        response.put("mensaje", mensaje);
        response.put("data", null);

        return Response.status(status).entity(response).build();
    
    }
    
    
}
