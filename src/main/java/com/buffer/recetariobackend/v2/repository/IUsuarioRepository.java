package com.buffer.recetariobackend.v2.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.buffer.recetariobackend.v2.entity.Usuario;

public interface IUsuarioRepository extends MongoRepository<Usuario, String> {

    Usuario findByUsernameAndPassword(String username, String password);
}
