package com.buffer.recetariobackend.controller;

import com.buffer.recetariobackend.dto.UsuarioDTO;
import com.buffer.recetariobackend.entity.Usuario;
import com.buffer.recetariobackend.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin()
@RestController
@RequestMapping("/api/users")
public class UsuarioController {
    @Autowired
    private IUsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody UsuarioDTO user){
        Usuario usuario = usuarioService.autenticarUsuario(user);
        if(usuario == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }

    @PostMapping("/create")
    public ResponseEntity<Usuario> register(@RequestBody UsuarioDTO user){
        Usuario usuario = usuarioService.crearUsuario(user);
        if (usuario == null) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(usuario);
    }
}
