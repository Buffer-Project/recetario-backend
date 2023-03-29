package com.buffer.recetariobackend.exception;

public class RecetaException extends RuntimeException {
    
private int status;

public RecetaException(String message, int status) {
    super(message);
    this.status = status;
}

public int getStatus() {
    return status;
}

}
