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
import javax.persistence.ManyToMany;

/**
 *
 * @author ericknavarro
 */
@Entity(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String nombre;
    @Column
    private String userName;
    @Column
    private String password;
    @Column
    private String email;
    @Column
    private String profile_Picture;
    @Column
    private int puntos;
    @Column
    private String rango; //TODO: Definir tipo de rangos
    /*@Column
    @ManyToMany
    private List<Post> posts;
    @Column
    @ManyToMany
    private List<User> follows;
    @Column
    @ManyToMany
    private List<User> followers;*/
    @Column
    private Date fecha_creacion;

    public User() {
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the profile_Picture
     */
    public String getProfile_Picture() {
        return profile_Picture;
    }

    /**
     * @param profile_Picture the profile_Picture to set
     */
    public void setProfile_Picture(String profile_Picture) {
        this.profile_Picture = profile_Picture;
    }

    /**
     * @return the puntos
     */
    public int getPuntos() {
        return puntos;
    }

    /**
     * @param puntos the puntos to set
     */
    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    /**
     * @return the rango
     */
    public String getRango() {
        return rango;
    }

    /**
     * @param rango the rango to set
     */
    public void setRango(String rango) {
        this.rango = rango;
    }

    /**
     * @return the fecha_creacion
     */
    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    /**
     * @param fecha_creacion the fecha_creacion to set
     */
    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }
    
    

}