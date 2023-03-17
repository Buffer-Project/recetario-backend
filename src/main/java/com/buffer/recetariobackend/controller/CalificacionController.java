package com.buffer.recetariobackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buffer.recetariobackend.entity.Calificacion;
import com.buffer.recetariobackend.entity.Receta;
import com.buffer.recetariobackend.service.ICalificacionService;

@CrossOrigin()

@RestController
@RequestMapping("/api/calificaciones")
public class CalificacionController {

  @Autowired
    private ICalificacionService calificacionService;

 
  @PostMapping("/{id}")
  public ResponseEntity<Receta> calificar(@PathVariable String id, @RequestBody Calificacion Calificacion ){
     Receta recetaConCalificacionNueva = calificacionService.calificar(id, Calificacion);

     if(recetaConCalificacionNueva == null){
      return ResponseEntity.notFound().build();
     }

      return ResponseEntity.ok(recetaConCalificacionNueva);
  }

  

        
      
    


}