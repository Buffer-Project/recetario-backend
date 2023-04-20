package com.buffer.recetariobackend.v2.exception;

public class UserNotAllowedException extends RecetaException {

    public UserNotAllowedException() {
        super("El usuario que desea realizar esta accion no es el autor del contenido",401);
        
    }
    
}
