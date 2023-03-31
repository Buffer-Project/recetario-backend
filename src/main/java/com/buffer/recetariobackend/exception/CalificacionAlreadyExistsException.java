package com.buffer.recetariobackend.exception;

public class CalificacionAlreadyExistsException extends RecetaException {

    public CalificacionAlreadyExistsException() {
        super("El autor de esta calificacion ya posee un comentario en esta receta",422);
    }
    
}
