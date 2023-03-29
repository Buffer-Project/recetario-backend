package com.buffer.recetariobackend.entity;

import org.springframework.data.annotation.Id;

public class Calificacion {

    private String comentario;

    private int puntuacion;
    @Id
    private String idCalificacion;

    public Calificacion(){}

    public Calificacion(String comentario, int puntuacion, String idCalificacion) {
        this.comentario = comentario;
        this.puntuacion = puntuacion;
        this.idCalificacion = idCalificacion;
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

    public String getIdCalificacion() {
        return idCalificacion;
    }

    public void setIdCalificacion(String idCalificacion) {
        this.idCalificacion = idCalificacion;
    }
}
