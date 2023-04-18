package com.buffer.recetariobackend.v2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.buffer.recetariobackend.v2.dto.UsuarioDTO;
import com.buffer.recetariobackend.v2.entity.Usuario;
import com.buffer.recetariobackend.v2.service.IUsuarioService;

@CrossOrigin()
@RestController
@RequestMapping("/api/v2")
public class UsuarioController {
    @Autowired
    private IUsuarioService usuarioService;

        @PostMapping("/users")
    public ResponseEntity<Usuario> register(@RequestBody UsuarioDTO user){
        Usuario usuario = usuarioService.crearUsuario(user);
        if (usuario == null) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(usuario);
    }
}
