package com.buffer.recetariobackend.v2.entity;

import org.springframework.data.annotation.Id;

public class Calificacion {

    private String comentario;

    private int puntuacion;

    private String idUser;

    @Id
    private String idCalificacion;

    public Calificacion() {
    }

    public Calificacion(String comentario, int puntuacion, String idUser) {
        this.comentario = comentario;
        this.puntuacion = puntuacion;
        this.idUser = idUser;
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

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    } 

    public String getIdCalificacion() {
        return idCalificacion;
    }

    public void setIdCalificacion(String idCalificacion) {
        this.idCalificacion = idCalificacion;
    }

}
