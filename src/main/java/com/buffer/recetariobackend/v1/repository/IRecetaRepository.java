package com.buffer.recetariobackend.v1.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.buffer.recetariobackend.v1.entity.Receta;

@Repository("v1.IRecetaRepository")
public interface IRecetaRepository extends MongoRepository<Receta, String> {

    long count();

}
