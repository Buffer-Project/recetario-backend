package com.buffer.recetariobackend.v2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buffer.recetariobackend.v2.dto.UsuarioDTO;
import com.buffer.recetariobackend.v2.entity.Calificacion;
import com.buffer.recetariobackend.v2.entity.Receta;
import com.buffer.recetariobackend.v2.exception.CalificacionAlreadyExistsException;
import com.buffer.recetariobackend.v2.exception.CalificacionNotFoundException;
import com.buffer.recetariobackend.v2.exception.RecetaNotFoundException;
import com.buffer.recetariobackend.v2.exception.UsuarioNotFoundException;

@Service
public class CalificacionService implements ICalificacionService {

    @Autowired
    private IRecetasService recetasService;

    @Autowired
    private IUsuarioService usuarioService;

    @Override
    public Receta calificar(String idReceta, Calificacion calificacion) {
        if (usuarioService.getUserById(calificacion.getIdUser()).isEmpty()) {
            throw new UsuarioNotFoundException();
        }

        Optional<Receta> recetaConCalificaciones = recetasService.getRecetaById(idReceta);
        if (recetaConCalificaciones.isEmpty()) {
            throw new RecetaNotFoundException(idReceta);
        }

        Receta receta = recetaConCalificaciones.get();
        List<Calificacion> calificaciones = receta.getCalificaciones();

        if (calificaciones.isEmpty()) {
            List<Calificacion> calificacionesAgregadas = new ArrayList<>();
            calificacionesAgregadas.add(calificacion);
            receta.setCalificaciones(calificacionesAgregadas);

        } else {

            for (Calificacion calif : calificaciones) {
                if (calif.getIdUser().equals(calificacion.getIdUser())) {
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
            while (!calificacionAEditar.getIdCalificacion().equals(calificacion.getIdCalificacion())
                    && index < calificaciones.size()) {
                calificacionAEditar = calificaciones.get(index);
                index += 1;
            }
            if (calificacionAEditar.getIdCalificacion().equals(calificacion.getIdCalificacion())) {
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
    public Receta deleteCalificacionByAutor(String idReceta, UsuarioDTO autor) {
        Optional<Receta> receta = recetasService.getRecetaById(idReceta);
        if (receta.isEmpty()) {
            throw new RecetaNotFoundException(idReceta);
        }
        Receta recetaFinal = receta.get();
        List<Calificacion> calificaciones = recetaFinal.getCalificaciones();
        List<Calificacion> listaFinal = new ArrayList<>();

        if (calificaciones == null) {
            throw new CalificacionNotFoundException();
        } else {
            int index = 0;
            Calificacion calif = calificaciones.get(index);
            while (!calif.getIdUser().equals(autor.getIdUsuarioDTO())
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
