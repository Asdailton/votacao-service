package br.com.adam.studyingspringboot.infra.exceptions;

public class PersonException extends RuntimeException{
    private String message;

    public PersonException(String message) {
       super(message);
    }
    public PersonException() {
        super("Person not found!");
    }
}
