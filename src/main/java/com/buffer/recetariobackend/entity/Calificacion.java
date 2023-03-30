package com.buffer.recetariobackend.entity;

import org.springframework.data.annotation.Id;

public class Calificacion {

    private String comentario;

    private int puntuacion;
    @Id
    private String autor;

    public Calificacion(){}

    public Calificacion(String comentario, int puntuacion, String autor) {
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

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}
