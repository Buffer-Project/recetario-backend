package com.buffer.recetariobackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buffer.recetariobackend.entity.Calificacion;
import com.buffer.recetariobackend.entity.Receta;
import com.buffer.recetariobackend.exception.RecetaNotFoundException;
import com.buffer.recetariobackend.exception.RecetaException;
import com.buffer.recetariobackend.service.ICalificacionService;

@CrossOrigin()

@RestController
@RequestMapping("/api/calificaciones")
public class CalificacionController {

  @Autowired
  private ICalificacionService calificacionService;

  @PatchMapping("/{id}")
  public ResponseEntity<Receta> modificarCalificacion(@PathVariable String id,@RequestBody Calificacion calificacion){
    
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
    } catch (RecetaException e) {
       return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(recetaConCalificacionNueva);
  }
      
    

}