package com.buffer.recetariobackend.v1.dto;

import java.util.List;

import com.buffer.recetariobackend.v1.entity.Calificacion;
import com.buffer.recetariobackend.v1.entity.Ingrediente;
import com.buffer.recetariobackend.v1.entity.Receta;

public class RecetaDTO {
    private String titulo;
    private String foto;
    private List<String> preparacion;

    private List<Ingrediente> ingredientes;

    private String dificultad;

    private List<Calificacion> calificaciones;

    public RecetaDTO() {
    }

    public RecetaDTO(String titulo, String foto, List<String> preparacion, List<Ingrediente> ingredientes, String dificultad, List<Calificacion> calificaciones) {
        this.titulo = titulo;
        this.foto = foto;
        this.preparacion = preparacion;
        this.ingredientes = ingredientes;
        this.dificultad = dificultad;
        this.calificaciones = calificaciones;
    }

    public Receta toReceta(){
        return new Receta(null, this.titulo, this.foto, this.preparacion, this.ingredientes, this.dificultad, this.calificaciones);
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public List<String> getPreparacion() {
        return preparacion;
    }

    public void setPreparacion(List<String> preparacion) {
        this.preparacion = preparacion;
    }

    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }
}
