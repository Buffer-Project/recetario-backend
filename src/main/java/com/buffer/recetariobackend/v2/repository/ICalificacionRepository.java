package com.buffer.recetariobackend.v2.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.buffer.recetariobackend.v2.entity.Calificacion;

public interface ICalificacionRepository extends MongoRepository<Calificacion, String> {
    
}
