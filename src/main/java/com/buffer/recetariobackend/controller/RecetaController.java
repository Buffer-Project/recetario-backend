package com.buffer.recetariobackend.controller;

import com.buffer.recetariobackend.dto.RecetaDTO;
import com.buffer.recetariobackend.entity.Receta;
import com.buffer.recetariobackend.service.IRecetasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin()
@RestController
@RequestMapping("/api/recetas")
public class RecetaController {

    @Autowired
    private IRecetasService recetasService;

    @GetMapping("")
    public ResponseEntity<List<Receta>> getRecetas(){
        List<Receta> recetas = recetasService.getRecetas();
        return ResponseEntity.ok(recetas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Receta> getRecetaById(@PathVariable String id){
        Optional<Receta> receta = recetasService.getRecetaById(id);
        return receta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Receta> deleteRecetaById(@PathVariable String id) {
        Optional<Receta> receta = recetasService.getRecetaById(id);
        if(receta.isPresent()) {
            recetasService.deleteRecetaById(id)
;            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    public ResponseEntity<Receta> create(@RequestBody RecetaDTO receta){
        Receta recetaNueva = recetasService.createReceta(receta);
        if(recetaNueva == null) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(recetaNueva);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Receta> update(@RequestBody Receta receta){
        Optional<Receta> rec = recetasService.getRecetaById(receta.getId());
        if(rec.isPresent()){
            recetasService.updateReceta(receta);
            return ResponseEntity.ok(receta);
        }
        return ResponseEntity.notFound().build();
    }
}
