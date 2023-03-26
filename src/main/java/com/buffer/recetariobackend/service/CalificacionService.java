package com.buffer.recetariobackend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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

    @Override
    public Receta modificarCalificacion(String idReceta, Calificacion calificacion) {
        Optional<Receta> recetaDraft = recetasService.getRecetaById(idReceta);
        if(recetaDraft.isEmpty()){
            throw new NullPointerException();
           }
        Receta receta = recetaDraft.get();
        List<Calificacion> calificaciones = receta.getCalificaciones();
        // evaluar la posibilidad de hacerlo con un while en vez del for
        for (Calificacion califAEditar : calificaciones) {
            if (califAEditar.getIdCalificacion() == calificacion.getIdCalificacion()) {
                califAEditar.setComentario(calificacion.getComentario());
                califAEditar.setPuntuacion(calificacion.getPuntuacion());

            }
        }

        return receta;
    }

}
