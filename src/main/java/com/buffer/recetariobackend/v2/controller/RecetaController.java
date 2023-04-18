package com.buffer.recetariobackend.v2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.buffer.recetariobackend.v2.dto.RecetaDTO;
import com.buffer.recetariobackend.v2.entity.Calificacion;
import com.buffer.recetariobackend.v2.entity.Receta;
import com.buffer.recetariobackend.v2.entity.Usuario;
import com.buffer.recetariobackend.v2.exception.CalificacionAlreadyExistsException;
import com.buffer.recetariobackend.v2.exception.CalificacionNotFoundException;
import com.buffer.recetariobackend.v2.exception.RecetaNotFoundException;
import com.buffer.recetariobackend.v2.service.ICalificacionService;
import com.buffer.recetariobackend.v2.service.IRecetasService;

import java.util.List;
import java.util.Optional;

@CrossOrigin()
@RestController
@RequestMapping("/api/v2/recetas")
public class RecetaController {

    @Autowired
    private ICalificacionService calificacionService;

    @Autowired
    private IRecetasService recetasService;

    @GetMapping("")
    public ResponseEntity<List<Receta>> getRecetas() {
        List<Receta> recetas = recetasService.getRecetas();
        return ResponseEntity.ok(recetas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Receta> getRecetaById(@PathVariable String id) {
        Optional<Receta> receta = recetasService.getRecetaById(id);
        return receta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Receta> deleteRecetaById(@PathVariable String id) {
        Optional<Receta> receta = recetasService.getRecetaById(id);
        if (receta.isPresent()) {
            recetasService.deleteRecetaById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    public ResponseEntity<Receta> create(@RequestBody RecetaDTO receta) {
        Receta recetaNueva = recetasService.createReceta(receta);
        if (recetaNueva == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(recetaNueva);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Receta> update(@RequestBody Receta receta) {
        Optional<Receta> rec = recetasService.getRecetaById(receta.getId());
        if (rec.isPresent()) {
            recetasService.updateReceta(receta);
            return ResponseEntity.ok(receta);
        }
        return ResponseEntity.notFound().build();
    }

    // METODOS PARA LAS CALIFICACIONES

    @PatchMapping("/{id}")
    public ResponseEntity<Receta> modificarCalificacion(@PathVariable String id,
            @RequestBody Calificacion calificacion) {

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
