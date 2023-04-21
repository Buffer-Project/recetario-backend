package com.buffer.recetariobackend.v2.service;

import java.util.Optional;

import com.buffer.recetariobackend.v2.entity.Calificacion;
import com.buffer.recetariobackend.v2.entity.Receta;

public interface ICalificacionService {

    public Optional<Calificacion> findCalificacionById(String idCalificacion);

    public Receta calificar(String idReceta, Calificacion calificacion);

    public Receta modificarCalificacion(String idReceta, Calificacion calificacion);

    public Receta deleteCalificacionByIdCalificacion(String idReceta, String idCalificacion, String idUser);
    
}
