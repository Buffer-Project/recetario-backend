package com.buffer.recetariobackend.v2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buffer.recetariobackend.v2.dto.UsuarioDTO;
import com.buffer.recetariobackend.v2.entity.Usuario;
import com.buffer.recetariobackend.v2.service.IUsuarioService;

@CrossOrigin()
@RestController
@RequestMapping("/api")
public class SessionController {

    @Autowired
    private IUsuarioService usuarioService;
    
    @PostMapping("/session")
    public ResponseEntity<Usuario> login(@RequestBody UsuarioDTO user){
        Usuario usuario = usuarioService.autenticarUsuario(user);
        if(usuario == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }
}
