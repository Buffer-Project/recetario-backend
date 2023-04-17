package com.buffer.recetariobackend.v2.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.buffer.recetariobackend.v1.entity.Receta;

@Repository
public interface IRecetaRepository extends MongoRepository<Receta, String> {

    long count();

}
