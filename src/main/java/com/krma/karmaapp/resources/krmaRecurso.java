/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krma.karmaapp.resources;

import com.krma.hibernateUtil.HibernateUtil;
import com.krma.karmaapp.models.User;
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
import org.hibernate.Transaction;
import org.hibernate.TransactionException;

/**
 *
 * @author ericknavarro
 */
@Path("users")
public class krmaRecurso {

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsersById(@PathParam("id") Integer id) {
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

        } finally {
            if (session != null) {
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
        Map<String, Object> response = new HashMap<>();

        int codigo = -1;
        String mensaje = "";
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
            if (session != null) {
                session.close();
            }
        }
        response.put("data", lista);
        response.put("mensaje", mensaje);

        return Response.status(codigo).entity(response).build();

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(User user) {

        Session session = null;
        Transaction tx = null;

        Map<String, Object> response = null;

        int codigo = Status.INTERNAL_SERVER_ERROR.getStatusCode();

        int codigoInt = 0;
        String mensaje = null;

        try {

            session = HibernateUtil.getSession();
            tx = session.beginTransaction();

            user.setFecha_creacion(new Date());
            session.save(user);
            tx.commit();

            codigo = Response.Status.CREATED.getStatusCode();
            codigoInt = 1;
            mensaje = "Se almaceno el user";

        } catch (TransactionException e) {
            if (tx != null) {
                tx.rollback();
            }
            mensaje = "Fallo en la transaccion";

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
        response.put("data", user);

        return Response.status(codigo).entity(response).build();

    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("id") Integer id, User user) {

        Session session = null;
        Transaction tx = null;
        Map<String, Object> response = null;

        int status = Status.INTERNAL_SERVER_ERROR.getStatusCode();
        int codigoInt = 0;
        String mensaje = null;

        User userUpdated = null;

        try {
            session = HibernateUtil.getSession();

            userUpdated = session.find(User.class, id);

            if (userUpdated != null) {
                tx = session.beginTransaction();
                
                
                userUpdated.setRango(user.getRango());
                userUpdated.setNombre(user.getNombre());
                userUpdated.setFollowers(user.getFollowers());
                userUpdated.setEmail(user.getEmail());
                userUpdated.setPuntos(user.getPuntos());
                userUpdated.setPassword(user.getPassword());
                userUpdated.setProfile_picture(user.getProfile_picture());
                userUpdated.setUsername(user.getUsername());

                session.update(userUpdated);
                tx.commit();

                status = Status.OK.getStatusCode();
                mensaje = "Se actualizo el recurso";
                codigoInt = 1;
            } else {
                status = Status.NOT_FOUND.getStatusCode();
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
        response.put("data", userUpdated);

        // Construir la respuesta
        return Response.status(status).entity(response).build();

    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("id") Integer id) {

        Session session = null;
        // ACID
        Transaction tx = null;
        User user = null;

        Map<String, Object> response = null;
        int status = Status.INTERNAL_SERVER_ERROR.getStatusCode();
        int codigo = 0;
        String mensaje = null;

        try {

            session = HibernateUtil.getSession();
            // Llevar de tarea get, load, find
            user = session.get(User.class, id);
            if (user != null) {
                tx = session.beginTransaction();
                session.remove(user);
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

}
