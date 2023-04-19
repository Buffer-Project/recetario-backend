package com.buffer.recetariobackend.v2.service;

import java.util.List;
import java.util.Optional;

import com.buffer.recetariobackend.v2.dto.RecetaDTO;
import com.buffer.recetariobackend.v2.entity.Receta;

public interface IRecetasService {

    List<Receta> getRecetas();

    Receta createReceta(RecetaDTO receta);

    Optional<Receta> getRecetaById(String idReceta);

    void deleteRecetaById(String idReceta);

    void updateReceta(Receta receta);
}
