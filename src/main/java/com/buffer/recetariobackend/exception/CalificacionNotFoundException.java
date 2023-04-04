package com.buffer.recetariobackend.exception;

public class CalificacionNotFoundException extends RecetaException {

    public CalificacionNotFoundException() {
        super("La calificacion que desea modificar no existe",404);
        
    }
    
}
