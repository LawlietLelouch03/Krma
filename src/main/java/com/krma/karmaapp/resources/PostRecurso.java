/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krma.karmaapp.resources;

/**
 *
 * @author soporte
 */
import com.krma.hibernateUtil.HibernateUtil;
import com.krma.karmaapp.models.Post;
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
import javax.ws.rs.core.Response.Status;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;

/**
 *
 * @author Admin
 */
@Path("posts")
public class PostRecurso {

    //Obtiene todos los post
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPosts() {
        Map<String, Object> response = new HashMap<>();

        int codigo = -1;
        String mensaje = "";
        SessionFactory sessionFactory = null;
        Session session = null;
        List<Post> lista = null;

        try {

            session = HibernateUtil.getSession();

            Query query = session.createQuery("FROM posts ", Post.class);
            lista = query.getResultList();

            codigo = Response.Status.OK.getStatusCode();
            mensaje = "Se obtuvieron los datos";
        } catch (Exception e) {
            e.printStackTrace();
            codigo = Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
            mensaje = e.getMessage();
        } finally {
            if (session != null) {
                session.close();
            }
            if (sessionFactory != null) {
                sessionFactory.close();
            }
        }

        response.put("data", lista);
        response.put("mensaje", mensaje);

        return Response.status(codigo).entity(response).build();
    }

    //Crea un post nuevo
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPost(Post post) {

        Session session = null;
        Transaction tx = null;

        Map<String, Object> response = null;

        // HTTP
        int codigo = Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();

        int codigoInt = 0;
        String mensaje = null;
        try {

            session = HibernateUtil.getSession();//1
            tx = session.beginTransaction();//2
            
            post.setFecha_creacion(new Date()); //!3
            session.save(post); //4
            
            tx.commit();// 5

            // Asignar datos de respuesta
            codigo = Response.Status.CREATED.getStatusCode();
            codigoInt = 1; // La operacion sucedio
            mensaje = "Se almaceno el post";

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
        response.put("data", post);

        return Response.status(codigo).entity(response).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePost(@PathParam("id") Integer id, Post post) {

        Session session = null;
        Transaction tx = null;
        Map<String, Object> response = null;

        int status = Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
        int codigoInt = 0;
        String mensaje = null;

        Post postDB = null;

        try {

            session = HibernateUtil.getSession();
            // Operaciones con la base de datos
            postDB = session.find(Post.class, id);

            if (postDB != null) {
                tx = session.beginTransaction();

                // De actualizar los datos el objeto hibernate
                postDB.setTitulo(post.getTitulo());
                postDB.setDescripcion(post.getDescripcion());
                postDB.setPuntos(post.getPuntos());
                postDB.setEstado(post.getEstado());
                postDB.setUbicacion(post.getUbicacion());
                postDB.setDificultad(post.getDificultad());
                
                postDB.setDeadLine(post.getDeadLine());
                postDB.setTiempo(post.getTiempo());
                postDB.setTags(post.getTags());

                session.update(postDB); // ALGO PUEDE PASAR AQUI
                tx.commit();

                status = Status.OK.getStatusCode();
                mensaje = "Se actualizo el recurso";
                codigoInt = 1;
            } else {
                status = Status.NOT_FOUND.getStatusCode();
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
            mensaje = "Error, cosulta mas tarde";
        } finally {
            if (session != null) {
                HibernateUtil.closeSession(session);
            }
        }

        // Configurar datos de respuesta
        response = new HashMap<>();
        response.put("codigo", codigoInt);
        response.put("mensaje", mensaje);
        response.put("data", postDB);

        // Construir la respuesta
        return Response.status(status).entity(response).build();
    }

    //Eliminar Post por id
    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePost(@PathParam("id") Integer id) {

        Session session = null;
        // ACID
        Transaction tx = null;
        Post post = null;

        Map<String, Object> response = null;
        int status = Status.INTERNAL_SERVER_ERROR.getStatusCode();
        int codigo = 0;
        String mensaje = null;

        try {

            session = HibernateUtil.getSession();
            // Llevar de tarea get, load, find
            post = session.get(Post.class, id);
            if (post != null) {
                tx = session.beginTransaction();
                session.remove(post);
                tx.commit();

                mensaje = "Recurso eliminado";
                status = Status.OK.getStatusCode();
            } else {
                status = Status.NOT_FOUND.getStatusCode();
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

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPostById(@PathParam("id") Integer id) {

        Session session = null;
        // ACID

        Post post = null;

        Map<String, Object> response = null;
        int status = Status.INTERNAL_SERVER_ERROR.getStatusCode();
        int codigo = 0;
        String mensaje = null;
        try {

            session = HibernateUtil.getSession();
            // Llevar de tarea get, load, find
            post = session.find(Post.class, id);

            if (post != null) {

                mensaje = "Recurso encontrado";
                status = Status.OK.getStatusCode();
            } else {
                status = Status.NOT_FOUND.getStatusCode();
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
                HibernateUtil.closeSession(session);
            }
        }

        response = new HashMap<>();
        response.put("codigo", codigo);
        response.put("mensaje", mensaje);
        response.put("data", post);

        return Response.status(status).entity(response).build();

    }

}