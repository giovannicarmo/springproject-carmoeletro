package com.giovannicarmo.projetocurso.carmoeletro.services.exception;

public class DataIntegrityException extends RuntimeException {
    private static final long seralVersionUID = 1L;

    public DataIntegrityException(String message) {
        super(message);
    }

    public DataIntegrityException(String message, Throwable cause) {
        super(message, cause);
    }

}
