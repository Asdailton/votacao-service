package br.com.adam.studyingspringboot.infra.exceptions;

public class RestauranteException extends RuntimeException {
    String message;
    public RestauranteException(String message){
        super(message);
    }
    public RestauranteException(){
        super("Restaurante não encontrado");
    }

}
