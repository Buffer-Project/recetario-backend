package com.buffer.recetariobackend.v2.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.buffer.recetariobackend.v2.dto.RecetaDTO;

import java.util.List;

@Document("recetas")
public class Receta {

    @Id
    private String idReceta;
    private String titulo;
    private String foto;
    private List<String> preparacion;

    private List<Ingrediente> ingredientes;

    private String dificultad;

    private List<Calificacion> calificaciones;

    public Receta() {
    }

    public Receta(String idReceta, String titulo, String foto, List<String> preparacion, List<Ingrediente> ingredientes,
                  String dificultad, List<Calificacion> calificaciones) {

        this.idReceta = idReceta;
        this.titulo = titulo;
        this.foto = foto;
        this.preparacion = preparacion;
        this.ingredientes = ingredientes;
        this.dificultad = dificultad;
        this.calificaciones = calificaciones;
    }

    public RecetaDTO toRecetaDTO() {
        return new RecetaDTO(this.titulo, this.foto, this.preparacion, this.ingredientes, this.dificultad, this.calificaciones);
    }

    public String getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(String idReceta) {
        this.idReceta = idReceta;
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

    public List<Calificacion> getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(List<Calificacion> calificaciones) {
        this.calificaciones = calificaciones;
    }

    
}
