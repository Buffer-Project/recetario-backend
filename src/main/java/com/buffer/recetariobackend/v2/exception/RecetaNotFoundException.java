package com.buffer.recetariobackend.v2.exception;

public class RecetaNotFoundException extends RecetaException {

    public RecetaNotFoundException(String idReceta) {
        super("No se encontro la receta con id" + idReceta, 404);
        
    }
    
}
