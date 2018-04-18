package com.giovannicarmo.projetocurso.carmoeletro.services.exception;

public class AuthorizationExcepition extends RuntimeException {
    private static final long seralVersionUID = 1L;

    public AuthorizationExcepition(String message) {
        super(message);
    }

    public AuthorizationExcepition(String message, Throwable cause) {
        super(message, cause);
    }

}
