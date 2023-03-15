package com.buffer.recetariobackend.entity;

import com.buffer.recetariobackend.dto.RecetaDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("recetas")
public class Receta {

    @Id
    private String id;
    private String titulo;
    private String foto;
    private List<String> preparacion;

    private List<Ingrediente> ingredientes;

    private String dificultad;

    private List<Calificacion> calificaciones;

    public Receta(String id, String titulo, String foto, List<String> preparacion, List<Ingrediente> ingredientes,
            String dificultad, List<Calificacion> calificaciones) {
        this.id = id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public void updateReceta(Receta receta) {
    }

    
}
