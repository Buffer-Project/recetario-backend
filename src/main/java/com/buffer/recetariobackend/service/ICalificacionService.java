package com.buffer.recetariobackend.service;

import com.buffer.recetariobackend.entity.Calificacion;
import com.buffer.recetariobackend.entity.Receta;

public interface ICalificacionService {

    public Receta modificarCalificacion(String idReceta, Calificacion calificacion);
}
