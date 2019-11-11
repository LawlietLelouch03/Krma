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
public class User {
    
    private int id;
    private String nombre;
    private String userName;
    private String password;
    private String profilePictureUrl;
    private int puntos;
    private String rango; //TODO: Definir tipo de rangos
    private List<Post> posts;
    private List<User> follows;
    private List<User> followers;
    private Date fecha;

    public User() {
    }

    public User(int id, String nombre, String userName, String password, String profilePictureUrl, int puntos, String rango, List<Post> posts, List<User> follows, List<User> followers, Date fecha) {
        this.id = id;
        this.nombre = nombre;
        this.userName = userName;
        this.password = password;
        this.profilePictureUrl = profilePictureUrl;
        this.puntos = puntos;
        this.rango = rango;
        this.posts = posts;
        this.follows = follows;
        this.followers = followers;
        this.fecha = fecha;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
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
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the imgPerfilUrl
     */
    public String getProfilePictureUrl() {
        return profilePictureUrl;
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
     * @return the posts
     */
    public List<Post> getPosts() {
        return posts;
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
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
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
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param profilePictureUrl the imgPerfilUrl to set
     */
    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
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
     * @param posts the posts to set
     */
    public void setPosts(List<Post> posts) {
        this.posts = posts;
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
    
    
    
}
