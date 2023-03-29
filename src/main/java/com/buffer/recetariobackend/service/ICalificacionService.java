package com.buffer.recetariobackend.service;

import com.buffer.recetariobackend.entity.Calificacion;
import com.buffer.recetariobackend.entity.Receta;

public interface ICalificacionService {

    public Receta modificarCalificacion(String idReceta, Calificacion calificacion);

    Receta deleteCalificacionByIdCalificacion(String idReceta, String idCalificacion);

    Receta calificar(String id, Calificacion calificacion);
}
