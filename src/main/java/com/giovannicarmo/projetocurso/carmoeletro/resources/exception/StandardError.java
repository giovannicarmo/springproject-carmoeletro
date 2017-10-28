package com.giovannicarmo.projetocurso.carmoeletro.resources.exception;

import java.io.Serializable;

public class StandardError implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer statusHttp;
    private String message;
    private Long timeStamp;

    public StandardError(Integer statusHttp, String message, Long timeStamp) {
        super();
        this.statusHttp = statusHttp;
        this.message = message;
        this.timeStamp = timeStamp;
    }

    public Integer getStatusHttp() {
        return statusHttp;
    }

    public void setStatusHttp(Integer statusHttp) {
        this.statusHttp = statusHttp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

}
