package com.buffer.recetariobackend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.buffer.recetariobackend.entity.Calificacion;
import com.buffer.recetariobackend.entity.Receta;

@Service
public class CalificacionService implements ICalificacionService {

    @Autowired
    private IRecetasService recetasService;

    @Override
    public Receta calificar(String id, Calificacion calificacion) {

        Optional<Receta> recetaConCalificaciones = recetasService.getRecetaById(id);
        if(recetaConCalificaciones.isEmpty()){
            throw new NullPointerException();
           }
        Receta receta = recetaConCalificaciones.get();
        List<Calificacion> calificaciones = receta.getCalificaciones();

        if (calificaciones == null) {
            List<Calificacion> calificacionesAgregadas = new ArrayList<>();
            calificacionesAgregadas.add(calificacion);
            receta.setCalificaciones(calificacionesAgregadas);

        } else {
            calificaciones.add(calificacion);
        }

        recetasService.updateReceta(receta);
        return receta;

    }

}
