package com.buffer.recetariobackend.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.buffer.recetariobackend.entity.Calificacion;
import com.buffer.recetariobackend.entity.Receta;

@Service
public interface ICalificacionService {

    public Receta calificar(String id, Calificacion calificacion);

    public Receta deleteCalificacionByIdCalificacion(String idReceta, String idCalificacion);
}
