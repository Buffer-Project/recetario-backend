package com.buffer.recetariobackend.v2.service;

import com.buffer.recetariobackend.v2.dto.UsuarioDTO;
import com.buffer.recetariobackend.v2.entity.Calificacion;
import com.buffer.recetariobackend.v2.entity.Receta;

public interface ICalificacionService {

    public Receta calificar(String idReceta, Calificacion calificacion);

    public Receta modificarCalificacion(String idReceta, Calificacion calificacion);

    public Receta deleteCalificacionByAutor(String idReceta, UsuarioDTO autor);
    
}
