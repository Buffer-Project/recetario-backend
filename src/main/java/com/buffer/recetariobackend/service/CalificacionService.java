package com.buffer.recetariobackend.service;

import java.util.ArrayList;
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
    public Receta calificar(String id, Calificacion calificacion) {

        Optional<Receta> recetaConCalificaciones = recetasService.getRecetaById(id);
        if(recetaConCalificaciones.isEmpty()){
            throw new RecetaNotFoundException(id);
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
            if (califAEditar.getAutor() == calificacion.getAutor()) {
                califAEditar.setComentario(calificacion.getComentario());
                califAEditar.setPuntuacion(calificacion.getPuntuacion());

            }
        }


        return receta;
    }
    
        @Override
    public Receta deleteCalificacionByAutor(String idReceta, String autor) {
        Optional<Receta> receta = recetasService.getRecetaById(idReceta);
        if(receta.isEmpty()){
         throw new NullPointerException();
        }
        Receta recetaFinal = receta.get();
        List<Calificacion> calificaciones = recetaFinal.getCalificaciones();
        List<Calificacion> listaFinal = new ArrayList<>();

        for (Calificacion calif : calificaciones) {
            if (calif.getAutor() != autor) {
                listaFinal.add(calif);
            }
        }

        recetaFinal.setCalificaciones(listaFinal);
        recetasService.updateReceta(recetaFinal);
        return recetaFinal;

    }

    

}
