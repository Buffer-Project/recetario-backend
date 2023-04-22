package com.buffer.recetariobackend.v2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buffer.recetariobackend.v2.dto.UsuarioDTO;
import com.buffer.recetariobackend.v2.entity.Calificacion;
import com.buffer.recetariobackend.v2.entity.Receta;
import com.buffer.recetariobackend.v2.exception.CalificacionAlreadyExistsException;
import com.buffer.recetariobackend.v2.exception.CalificacionNotFoundException;
import com.buffer.recetariobackend.v2.exception.RecetaNotFoundException;
import com.buffer.recetariobackend.v2.exception.UserNotAllowedException;
import com.buffer.recetariobackend.v2.exception.UsuarioNotFoundException;
import com.buffer.recetariobackend.v2.repository.ICalificacionRepository;

import ch.qos.logback.core.filter.Filter;

@Service
public class CalificacionService implements ICalificacionService {

    @Autowired
    private IRecetasService recetasService;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private ICalificacionRepository calificacionRepository;

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

        if (usuarioService.getUserById(calificacion.getIdUser()).isEmpty()) {
            throw new UsuarioNotFoundException();
        }

        Optional<Receta> recetaDraft = recetasService.getRecetaById(idReceta);
        if (recetaDraft.isEmpty()) {
            throw new RecetaNotFoundException(idReceta);
        }
        Receta receta = recetaDraft.get();

        if (receta.getCalificaciones().isEmpty()) {
            throw new CalificacionNotFoundException();
        } else {

            Optional<Calificacion> calificacionAEditar = findCalificacionById(calificacion.getIdCalificacion());

            if (calificacionAEditar.isEmpty()) {
                throw new CalificacionNotFoundException();
            }

            Calificacion califFinal = calificacionAEditar.get();

            if (!califFinal.getIdUser().equals(calificacion.getIdUser())) {
                throw new UserNotAllowedException();
            }
            califFinal.setComentario(calificacion.getComentario());
            califFinal.setPuntuacion(calificacion.getPuntuacion());
        }
        recetasService.updateReceta(receta);
        return receta;
    }

    @Override
    public Receta deleteCalificacionByIdCalificacion(String idReceta, String idCalificacion, String idUser) {

        if (usuarioService.getUserById(idUser).isEmpty()) {
            throw new UsuarioNotFoundException();
        }

        Optional<Receta> receta = recetasService.getRecetaById(idReceta);
        if (receta.isEmpty()) {
            throw new RecetaNotFoundException(idReceta);
        }

        Receta recetaFinal = receta.get();
        List<Calificacion> calificaciones = recetaFinal.getCalificaciones();

        if (calificaciones.isEmpty()) {
            throw new CalificacionNotFoundException();
        } else {
            Optional<Calificacion> califABorrar = findCalificacionById(idCalificacion);
            if (califABorrar.isEmpty()) {
                throw new CalificacionNotFoundException();
            } else {
                Calificacion califFinal = califABorrar.get();

                List<Calificacion> listadoFinal = calificaciones.stream()
                        .filter(calificacion -> !calificacion.getIdCalificacion()
                        .equals(califFinal.getIdCalificacion()))
                        .collect(Collectors.toList());

                recetaFinal.setCalificaciones(listadoFinal);
            }
        }
        recetasService.updateReceta(recetaFinal);
        return recetaFinal;
    }

    @Override
    public Optional<Calificacion> findCalificacionById(String idCalificacion) {
        return calificacionRepository.findById(idCalificacion);
    }

}
