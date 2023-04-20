package com.buffer.recetariobackend.v2.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buffer.recetariobackend.v2.dto.UsuarioDTO;
import com.buffer.recetariobackend.v2.entity.Usuario;
import com.buffer.recetariobackend.v2.repository.IUsuarioRepository;

@Service
public class UsuarioService implements IUsuarioService{

    @Autowired
    private IUsuarioRepository usuarioRepository;
    @Override
    public Usuario autenticarUsuario(UsuarioDTO user) {
        return usuarioRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    @Override
    public Usuario crearUsuario(UsuarioDTO user) {
        return usuarioRepository.save(new Usuario(null, user.getUsername(), user.getPassword(), user.getName()));
    }

    @Override
    public Optional<Usuario> getUserById(String idUser) {
        return usuarioRepository.findById(idUser);
    }
}
