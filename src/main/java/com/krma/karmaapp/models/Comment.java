/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krma.karmaapp.models;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 *
 * @author ericknavarro
 */
@Entity(name = "comments")
public class Comment {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    @JoinColumn(name = "post")
    private Post post;
    @OneToOne
    @JoinColumn(name = "autor")
    private User autor;
    @Column
    private String contenido;
    @Column
    private Date fecha;

    
    
    public Comment() {
    }

    /**
     * @return the autor
     */
    public User getAutor() {
        return autor;
    }

    /**
     * @return the contenido
     */
    public String getContenido() {
        return contenido;
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
     * @return the post
     */
    public Post getPost() {
        return post;
    }

    /**
     * @param autor the autor to set
     */
    public void setAutor(User autor) {
        this.autor = autor;
    }

    /**
     * @param contenido the contenido to set
     */
    public void setContenido(String contenido) {
        this.contenido = contenido;
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
     * @param post the post to set
     */
    public void setPost(Post post) {
        this.post = post;
    }
    
    
    
}
