/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krma.karmaapp.models;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author ericknavarro
 */
public class Post {
    
    private int id;
    private String titulo;
    private String descripcion;
    private User owner;
    private int putnos;
    private String estado;
    private String ubicacion;
    private String dificultad; //TODO: Definir dificultades
    private List<User> postulantes;
    private List<Comment> comentarios;
    private Date fecha;
    private Date dateLine;
    private String tiempo; //TODO: Definir tipo de dato de timpo
    private String tags; //TODO: Definir tags

    public Post() {
    }

    public Post(int id, String titulo, String descripcion, User owner, int putnos, String estado, String ubicacion, String dificultad, List<User> postulantes, List<Comment> comentarios, Date fecha, Date dateLine, String tiempo, String tags) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.owner = owner;
        this.putnos = putnos;
        this.estado = estado;
        this.ubicacion = ubicacion;
        this.dificultad = dificultad;
        this.postulantes = postulantes;
        this.comentarios = comentarios;
        this.fecha = fecha;
        this.dateLine = dateLine;
        this.tiempo = tiempo;
        this.tags = tags;
    }

    /**
     * @return the comentarios
     */
    public List<Comment> getComentarios() {
        return comentarios;
    }

    /**
     * @return the dateLine
     */
    public Date getDateLine() {
        return dateLine;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @return the dificultad
     */
    public String getDificultad() {
        return dificultad;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the owner
     */
    public User getOwner() {
        return owner;
    }

    /**
     * @return the postulantes
     */
    public List<User> getPostulantes() {
        return postulantes;
    }

    /**
     * @return the putnos
     */
    public int getPutnos() {
        return putnos;
    }

    /**
     * @return the tags
     */
    public String getTags() {
        return tags;
    }

    /**
     * @return the tiempo
     */
    public String getTiempo() {
        return tiempo;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @return the ubicacion
     */
    public String getUbicacion() {
        return ubicacion;
    }

    /**
     * @param comentarios the comentarios to set
     */
    public void setComentarios(List<Comment> comentarios) {
        this.comentarios = comentarios;
    }

    /**
     * @param dateLine the dateLine to set
     */
    public void setDateLine(Date dateLine) {
        this.dateLine = dateLine;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @param dificultad the dificultad to set
     */
    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param owner the owner to set
     */
    public void setOwner(User owner) {
        this.owner = owner;
    }

    /**
     * @param postulantes the postulantes to set
     */
    public void setPostulantes(List<User> postulantes) {
        this.postulantes = postulantes;
    }

    /**
     * @param putnos the putnos to set
     */
    public void setPutnos(int putnos) {
        this.putnos = putnos;
    }

    /**
     * @param tags the tags to set
     */
    public void setTags(String tags) {
        this.tags = tags;
    }

    /**
     * @param tiempo the tiempo to set
     */
    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @param ubicacion the ubicacion to set
     */
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    
    
    
}
