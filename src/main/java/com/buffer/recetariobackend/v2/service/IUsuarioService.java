package com.buffer.recetariobackend.v2.service;

import com.buffer.recetariobackend.v2.dto.UsuarioDTO;
import com.buffer.recetariobackend.v2.entity.Usuario;

public interface IUsuarioService {

    Usuario autenticarUsuario(UsuarioDTO user);
    Usuario crearUsuario(UsuarioDTO user);
}
