package com.buffer.recetariobackend.v1.service;

import com.buffer.recetariobackend.v1.dto.UsuarioDTO;
import com.buffer.recetariobackend.v1.entity.Usuario;

public interface IUsuarioService {

    Usuario autenticarUsuario(UsuarioDTO user);
    Usuario crearUsuario(UsuarioDTO user);
}
