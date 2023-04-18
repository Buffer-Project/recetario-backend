package com.buffer.recetariobackend.v2.service;

import com.buffer.recetariobackend.v2.entity.Calificacion;
import com.buffer.recetariobackend.v2.entity.Receta;
import com.buffer.recetariobackend.v2.entity.Usuario;

public interface ICalificacionService {

    public Receta modificarCalificacion(String idReceta, Calificacion calificacion);

    public Receta deleteCalificacionByAutor(String idReceta, Usuario autor);

    Receta calificar(String id, Calificacion calificacion);
}
