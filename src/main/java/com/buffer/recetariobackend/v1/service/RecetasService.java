package com.buffer.recetariobackend.v1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buffer.recetariobackend.v1.dto.RecetaDTO;
import com.buffer.recetariobackend.v1.entity.Receta;
import com.buffer.recetariobackend.v1.repository.IRecetaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RecetasService implements IRecetasService {

    @Autowired
    private IRecetaRepository recetaRepository;

    @Override
    public List<Receta> getRecetas() {
        return recetaRepository.findAll();
    }

    @Override
    public Receta createReceta(RecetaDTO receta) {
        return recetaRepository.save(receta.toReceta());
    }

    @Override
    public Optional<Receta> getRecetaById(String id) {
        return recetaRepository.findById(id);
    }

    @Override
    public void deleteRecetaById(String id) {
        recetaRepository.deleteById(id);
    }

    @Override
    public void updateReceta(Receta receta) {
        recetaRepository.save(receta);
    }

    

}
