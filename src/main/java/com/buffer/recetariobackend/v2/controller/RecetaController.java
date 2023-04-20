package com.buffer.recetariobackend.v2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.buffer.recetariobackend.v2.dto.RecetaDTO;
import com.buffer.recetariobackend.v2.dto.UsuarioDTO;
import com.buffer.recetariobackend.v2.entity.Calificacion;
import com.buffer.recetariobackend.v2.entity.Receta;
import com.buffer.recetariobackend.v2.exception.CalificacionAlreadyExistsException;
import com.buffer.recetariobackend.v2.exception.CalificacionNotFoundException;
import com.buffer.recetariobackend.v2.exception.RecetaNotFoundException;
import com.buffer.recetariobackend.v2.exception.UsuarioNotFoundException;
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
    public ResponseEntity<Receta> getRecetaById(@PathVariable String idReceta) {
        Optional<Receta> receta = recetasService.getRecetaById(idReceta);
        return receta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Receta> deleteRecetaById(@PathVariable String idReceta) {
        Optional<Receta> receta = recetasService.getRecetaById(idReceta);
        if (receta.isPresent()) {
            recetasService.deleteRecetaById(idReceta);
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
        Optional<Receta> rec = recetasService.getRecetaById(receta.getIdReceta());
        if (rec.isPresent()) {
            recetasService.updateReceta(receta);
            return ResponseEntity.ok(receta);
        }
        return ResponseEntity.notFound().build();
    }

    // METODOS PARA LAS CALIFICACIONES

    @PostMapping("/{idReceta}/calificaciones")
    public ResponseEntity<Receta> calificar(@PathVariable String idReceta, @RequestBody Calificacion calificacion) {
        Receta recetaConCalificacionNueva = null;
        try {
            recetaConCalificacionNueva = calificacionService.calificar(idReceta, calificacion);
        } catch (CalificacionAlreadyExistsException er) {
            return ResponseEntity.unprocessableEntity().build();
        } catch (RecetaNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (UsuarioNotFoundException err){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(recetaConCalificacionNueva);
    }

    @PatchMapping("/{id}/calificaciones")
    public ResponseEntity<Receta> modificarCalificacion(@PathVariable String idReceta,
            @RequestBody Calificacion calificacion) {

        Receta recetaFinal = null;
        try {
            recetaFinal = calificacionService.modificarCalificacion(idReceta, calificacion);
        } catch (RecetaNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (CalificacionNotFoundException er) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recetaFinal);

    }

    @DeleteMapping("/{id}/calificaciones")
    public ResponseEntity<Receta> deleteCalificacionByAutor(@PathVariable String idReceta,
            @RequestBody UsuarioDTO autor) {
        Receta recetaFinal = null;
        try {
            recetaFinal = calificacionService.deleteCalificacionByAutor(idReceta, autor);
        } catch (RecetaNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (CalificacionNotFoundException er) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(recetaFinal);

    }
}
