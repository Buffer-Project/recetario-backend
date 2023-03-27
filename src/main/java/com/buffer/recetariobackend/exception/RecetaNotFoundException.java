package com.buffer.recetariobackend.exception;

public class RecetaNotFoundException extends RecetaException {

    public RecetaNotFoundException(String idReceta) {
        super("No se encontro la receta con id" + idReceta, 404);
        
    }
    
}
