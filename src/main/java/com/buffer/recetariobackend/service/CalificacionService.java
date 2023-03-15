package com.buffer.recetariobackend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.buffer.recetariobackend.entity.Calificacion;
import com.buffer.recetariobackend.entity.Receta;
import com.buffer.recetariobackend.repository.IRecetaRepository;
import com.mongodb.internal.operation.CreateCollectionOperation;

@Service
public class CalificacionService implements ICalificacionService {

    @Autowired
    private IRecetaRepository recetaRepository;

    @Autowired
    private IRecetasService recetasService;

    @Override
    public Receta calificar(String id, Calificacion calificacion) {

        Optional<Receta> recetaConCalificaciones = recetasService.getRecetaById(id);

        Receta receta = recetaConCalificaciones.get();

        List<Calificacion> calificaciones = receta.getCalificaciones();

        calificaciones.add(calificacion);

        receta.updateReceta(receta);

        return receta;

    }

    
}
