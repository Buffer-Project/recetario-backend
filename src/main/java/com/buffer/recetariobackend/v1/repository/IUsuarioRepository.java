package com.buffer.recetariobackend.v1.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.buffer.recetariobackend.v1.entity.Usuario;
import org.springframework.stereotype.Repository;

@Repository("v1.IUsuarioRepository")
public interface IUsuarioRepository extends MongoRepository<Usuario, String> {

    Usuario findByUsernameAndPassword(String username, String password);
}
