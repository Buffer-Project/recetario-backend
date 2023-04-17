package com.buffer.recetariobackend.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.buffer.recetariobackend.v1.entity.Calificacion;
import com.buffer.recetariobackend.v1.entity.Receta;
import com.buffer.recetariobackend.v1.entity.Usuario;
import com.buffer.recetariobackend.v1.exception.CalificacionAlreadyExistsException;
import com.buffer.recetariobackend.v1.exception.CalificacionNotFoundException;
import com.buffer.recetariobackend.v1.exception.RecetaNotFoundException;
import com.buffer.recetariobackend.v1.service.ICalificacionService;

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
    } catch (CalificacionNotFoundException er) {
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
  public ResponseEntity<Receta> deleteCalificacionByAutor(@PathVariable String id, @RequestBody Usuario autor) {
    Receta recetaFinal = null;
    try {
      recetaFinal = calificacionService.deleteCalificacionByAutor(id, autor);
    } catch (RecetaNotFoundException e) {
      return ResponseEntity.notFound().build();
    } catch (CalificacionNotFoundException er) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(recetaFinal);

  }
}