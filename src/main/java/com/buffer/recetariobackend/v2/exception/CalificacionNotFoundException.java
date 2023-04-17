package com.buffer.recetariobackend.v2.exception;

public class CalificacionNotFoundException extends RecetaException {

    public CalificacionNotFoundException() {
        super("La calificacion que desea modificar no existe",404);
        
    }
    
}
