package com.buffer.recetariobackend.service;

import com.buffer.recetariobackend.entity.Calificacion;
import com.buffer.recetariobackend.entity.Receta;
import com.buffer.recetariobackend.entity.Usuario;

public interface ICalificacionService {

    public Receta modificarCalificacion(String idReceta, Calificacion calificacion);

    public Receta deleteCalificacionByAutor(String idReceta, Usuario autor);

    Receta calificar(String id, Calificacion calificacion);
}
