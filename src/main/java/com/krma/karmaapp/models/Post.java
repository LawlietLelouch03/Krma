/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krma.karmaapp.models;

import java.sql.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author ericknavarro
 */
@Entity
public class Post {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String titulo;
    @Column
    private String descripcion;
    /*@Column
    private User owner*/
    @Column
    private int putnos;
    @Column
    private String estado;
    @Column
    private String ubicacion;
    @Column
    private String dificultad; //TODO: Definir dificultades
    /*@Column
    private List<User> postulantes;
    @Column
    private List<Comment> comentarios;*/
    @Column
    private Date fecha;
    @Column
    private Date deadLine;
    @Column
    private String tiempo; //TODO: Definir tipo de dato de timpo
    @Column
    private String tags; //TODO: Definir tags

    public Post() {
    }

    /**
     * @return the deadLine
     */
    public Date getDeadLine() {
        return deadLine;
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
     * @param deadLine the deadLine to set
     */
    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
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
