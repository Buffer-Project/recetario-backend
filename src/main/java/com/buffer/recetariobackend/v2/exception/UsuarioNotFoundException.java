package com.buffer.recetariobackend.v2.exception;

public class UsuarioNotFoundException extends RecetaException {

    public UsuarioNotFoundException() {
        super("El usuario ingresado no existe", 404);
        
    }
    
}
