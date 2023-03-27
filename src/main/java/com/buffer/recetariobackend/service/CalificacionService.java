package com.buffer.recetariobackend.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.buffer.recetariobackend.entity.Calificacion;
import com.buffer.recetariobackend.entity.Receta;
import com.buffer.recetariobackend.exception.RecetaNotFoundException;

@Service
public class CalificacionService implements ICalificacionService {

    @Autowired
    private IRecetasService recetasService;
  

    @Override
    public Receta modificarCalificacion(String idReceta, Calificacion calificacion) {
        Optional<Receta> recetaDraft = recetasService.getRecetaById(idReceta);
        if(recetaDraft.isEmpty()){
            throw new RecetaNotFoundException(idReceta);
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
