package com.buffer.recetariobackend.service;

import com.buffer.recetariobackend.entity.Calificacion;
import com.buffer.recetariobackend.entity.Receta;

public interface ICalificacionService {

    public Receta modificarCalificacion(String idReceta, Calificacion calificacion);

    Receta deleteCalificacionByAutor(String idReceta, String autor);

    Receta calificar(String id, Calificacion calificacion);
}
