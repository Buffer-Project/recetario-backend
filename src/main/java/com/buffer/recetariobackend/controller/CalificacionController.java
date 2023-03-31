package com.buffer.recetariobackend.controller;

import com.buffer.recetariobackend.entity.Calificacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.buffer.recetariobackend.entity.Receta;
import com.buffer.recetariobackend.entity.Usuario;
import com.buffer.recetariobackend.exception.RecetaNotFoundException;
import com.buffer.recetariobackend.exception.CalificacionAlreadyExistsException;
import com.buffer.recetariobackend.service.ICalificacionService;

@CrossOrigin()

@RestController
@RequestMapping("/api/calificaciones")
public class CalificacionController {

  @Autowired
  private ICalificacionService calificacionService;

  @PatchMapping("/{id}")
  public ResponseEntity<Receta> modificarCalificacion(@PathVariable String id, @RequestBody Calificacion calificacion) {

    Receta recetaFinal = null;
    try {
      recetaFinal = calificacionService.modificarCalificacion(id, calificacion);
    } catch (RecetaNotFoundException e) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(recetaFinal);

  }

  @PostMapping("/{id}")
  public ResponseEntity<Receta> calificar(@PathVariable String id, @RequestBody Calificacion calificacion) {
    Receta recetaConCalificacionNueva = null;
    try {
      recetaConCalificacionNueva = calificacionService.calificar(id, calificacion);
    } catch (CalificacionAlreadyExistsException er) {
      return ResponseEntity.unprocessableEntity().build();
    } catch (RecetaNotFoundException e) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(recetaConCalificacionNueva);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Receta> deleteCalificacionByAutor(String id, Usuario autor) {
    Receta recetaFinal = null;
    try {
      recetaFinal = calificacionService.deleteCalificacionByAutor(id, autor);
    } catch (NullPointerException e) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(recetaFinal);

  }
}