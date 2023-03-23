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
        Receta receta = recetaConCalificaciones.get();
        List<Calificacion> calificaciones = receta.getCalificaciones();
        calificaciones.add(calificacion);
        recetasService.updateReceta(receta);
        return receta;

    }

    @Override
    public Receta deleteCalificacionByIdCalificacion(String idReceta, String idCalificacion) {
        Optional<Receta> receta = recetasService.getRecetaById(idReceta);
        if(receta.isEmpty()){
         throw new NullPointerException();
        }
        Receta recetaFinal = receta.get();
        List<Calificacion> calificaciones = recetaFinal.getCalificaciones();
        List<Calificacion> listaFinal = new ArrayList<>();

        for (Calificacion calif : calificaciones) {
            if (calif.getIdCalificacion() != idCalificacion) {
                listaFinal.add(calif);
            }
        }

        recetaFinal.setCalificaciones(listaFinal);
        recetasService.updateReceta(recetaFinal);
        return recetaFinal;

    }

    
}
