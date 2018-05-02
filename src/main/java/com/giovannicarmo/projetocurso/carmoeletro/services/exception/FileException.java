package com.giovannicarmo.projetocurso.carmoeletro.services.exception;

public class FileExcepition extends RuntimeException {
    private static final long seralVersionUID = 1L;

    public FileExcepition(String message) {
        super(message);
    }

    public FileExcepition(String message, Throwable cause) {
        super(message, cause);
    }

}
