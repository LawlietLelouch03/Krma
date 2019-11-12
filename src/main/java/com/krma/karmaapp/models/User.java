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
@Entity
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
    private String profilePictureUrl;
    @Column
    private int puntos;
    @Column
    private String rango; //TODO: Definir tipo de rangos
    @Column
    @ManyToMany
    private List<Post> posts;
    @Column
    @ManyToMany
    private List<User> follows;
    @Column
    @ManyToMany
    private List<User> followers;
    @Column
    private Date fecha;

    public User() {
    }

    
    
    /**
     * @return the email
     */
    public String getEmail() {
        return email;
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
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return the profilePictureUrl
     */
    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    /**
     * @return the puntos
     */
    public int getPuntos() {
        return puntos;
    }

    /**
     * @return the rango
     */
    public String getRango() {
        return rango;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
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
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @param profilePictureUrl the profilePictureUrl to set
     */
    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    /**
     * @param puntos the puntos to set
     */
    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    /**
     * @param rango the rango to set
     */
    public void setRango(String rango) {
        this.rango = rango;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the followers
     */
    public List<User> getFollowers() {
        return followers;
    }

    /**
     * @return the follows
     */
    public List<User> getFollows() {
        return follows;
    }

    /**
     * @return the posts
     */
    public List<Post> getPosts() {
        return posts;
    }

    /**
     * @param followers the followers to set
     */
    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    /**
     * @param follows the follows to set
     */
    public void setFollows(List<User> follows) {
        this.follows = follows;
    }

    /**
     * @param posts the posts to set
     */
    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
    
    

}