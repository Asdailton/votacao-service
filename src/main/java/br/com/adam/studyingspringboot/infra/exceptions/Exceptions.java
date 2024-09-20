package br.com.adam.studyingspringboot.infra.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class Exceptions extends ResponseEntityExceptionHandler {
    @ExceptionHandler(UserException.class)
    public ResponseEntity<RestErrorMessage> error404UserNotFound(UserException exception, WebRequest request){
        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return new ResponseEntity<>(threatResponse, new HttpHeaders(), threatResponse.getStatus());
    }
    @ExceptionHandler(PersonException.class)
        public ResponseEntity<RestErrorMessage> error404PersonNotFound(PersonException exception, WebRequest request){

        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return new ResponseEntity<>(threatResponse, new HttpHeaders(), threatResponse.getStatus());
    }

    private record ErrorValidation(String field, String content){
        public ErrorValidation(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }

    }


}
