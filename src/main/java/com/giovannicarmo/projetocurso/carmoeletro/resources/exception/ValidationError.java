package com.giovannicarmo.projetocurso.carmoeletro.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
    private static final long serialVersionUID = 1L;

    List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(Integer statusHttp, String message, Long timeStamp) {
        super(statusHttp, message, timeStamp);
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }

    public void addError(String fieldName, String message) {
        errors.add(new FieldMessage(fieldName, message));
    }

}
