package com.buffer.recetariobackend.exception;

public class RecetaNotFoundException extends RecetaException {

    public RecetaNotFoundException(String id) {
        super("No se encontró la receta con id " + id, 404);
    }


    
}
