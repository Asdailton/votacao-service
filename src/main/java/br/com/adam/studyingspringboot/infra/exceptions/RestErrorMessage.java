package br.com.adam.studyingspringboot.infra.exceptions;

import org.springframework.http.HttpStatus;

public class RestErrorMessage {
    public RestErrorMessage(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    private HttpStatus status;

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;
}
