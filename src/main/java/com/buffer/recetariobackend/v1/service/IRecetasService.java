package com.buffer.recetariobackend.v1.service;

import java.util.List;
import java.util.Optional;

import com.buffer.recetariobackend.v1.dto.RecetaDTO;
import com.buffer.recetariobackend.v1.entity.Receta;

public interface IRecetasService {

    List<Receta> getRecetas();

    Receta createReceta(RecetaDTO receta);

    Optional<Receta> getRecetaById(String id);

    void deleteRecetaById(String id);

    void updateReceta(Receta receta);
}
