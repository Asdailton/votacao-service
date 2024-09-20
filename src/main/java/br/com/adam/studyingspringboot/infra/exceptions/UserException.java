package br.com.adam.studyingspringboot.infra.exceptions;

public class UserException extends RuntimeException{
    private String message;

    public UserException(long id) {
        super(String.format("User with id %d not found", id));

    }

}
