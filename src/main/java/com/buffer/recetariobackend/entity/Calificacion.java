package com.buffer.recetariobackend.entity;

public class Calificacion {

    private String comentario;

    private int puntuacion;
    
    private Usuario autor;

    public Calificacion(){}

    public Calificacion(String comentario, int puntuacion, Usuario autor) {
        this.comentario = comentario;
        this.puntuacion = puntuacion;
        this.autor = autor;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    
}
