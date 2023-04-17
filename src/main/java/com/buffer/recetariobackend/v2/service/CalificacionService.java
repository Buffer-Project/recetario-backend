package com.buffer.recetariobackend.v2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buffer.recetariobackend.v1.entity.Calificacion;
import com.buffer.recetariobackend.v1.entity.Receta;
import com.buffer.recetariobackend.v1.entity.Usuario;
import com.buffer.recetariobackend.v1.exception.CalificacionAlreadyExistsException;
import com.buffer.recetariobackend.v1.exception.CalificacionNotFoundException;
import com.buffer.recetariobackend.v1.exception.RecetaNotFoundException;

@Service
public class CalificacionService implements ICalificacionService {

    @Autowired
    private IRecetasService recetasService;

    @Override
    public Receta calificar(String id, Calificacion calificacion) {

        Optional<Receta> recetaConCalificaciones = recetasService.getRecetaById(id);
        if (recetaConCalificaciones.isEmpty()) {
            throw new RecetaNotFoundException(id);
        }
        Receta receta = recetaConCalificaciones.get();
        List<Calificacion> calificaciones = receta.getCalificaciones();

        if (calificaciones == null) {
            List<Calificacion> calificacionesAgregadas = new ArrayList<>();
            calificacionesAgregadas.add(calificacion);
            receta.setCalificaciones(calificacionesAgregadas);

        } else {
            for (Calificacion calif : calificaciones) {
                if (calif.getAutor().getId().equals(calificacion.getAutor().getId())) {
                    throw new CalificacionAlreadyExistsException();
                } else {
                    calificaciones.add(calificacion);
                }

            }

        }

        recetasService.updateReceta(receta);
        return receta;
    }

    @Override
    public Receta modificarCalificacion(String idReceta, Calificacion calificacion) {
        Optional<Receta> recetaDraft = recetasService.getRecetaById(idReceta);
        if (recetaDraft.isEmpty()) {
            throw new RecetaNotFoundException(idReceta);
        }
        Receta receta = recetaDraft.get();
        List<Calificacion> calificaciones = receta.getCalificaciones();
        if (calificaciones == null) {
            throw new CalificacionNotFoundException();
        } else {
            int index = 0;
            Calificacion calificacionAEditar = calificaciones.get(index);
            while (!calificacionAEditar.getAutor().getId().equals(calificacion.getAutor().getId())
                    && index < calificaciones.size()) {
                calificacionAEditar = calificaciones.get(index);
                index += 1;
            }
            if (calificacionAEditar.getAutor().getId().equals(calificacion.getAutor().getId())) {
                calificacionAEditar.setComentario(calificacion.getComentario());
                calificacionAEditar.setPuntuacion(calificacion.getPuntuacion());
            }
            if (index == calificaciones.size()) {
                throw new CalificacionNotFoundException();

            }

        }

        return receta;
    }

    @Override
    public Receta deleteCalificacionByAutor(String id, Usuario autor) {
        Optional<Receta> receta = recetasService.getRecetaById(id);
        if (receta.isEmpty()) {
            throw new RecetaNotFoundException(id);
        }
        Receta recetaFinal = receta.get();
        List<Calificacion> calificaciones = recetaFinal.getCalificaciones();
        List<Calificacion> listaFinal = new ArrayList<>();

        if (calificaciones == null) {
            throw new CalificacionNotFoundException();
        } else {
            int index = 0;
            Calificacion calif = calificaciones.get(index);
            while (!calif.getAutor().getId().equals(autor.getId())
                    && index < calificaciones.size()) {
                listaFinal.add(calif);
                calif = calificaciones.get(index);
                index += 1;
            }

            if (index == calificaciones.size()) {
                throw new CalificacionNotFoundException();

            }
            recetaFinal.setCalificaciones(listaFinal);
        }

        recetasService.updateReceta(recetaFinal);
        return recetaFinal;
    }
}
