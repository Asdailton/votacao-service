package br.com.adam.studyingspringboot.infra.exceptions;

import java.time.LocalDate;

public class VoteException extends RuntimeException {
    public VoteException(String message) {
        super(message);
    }

    public VoteException(Long restauranteId, String registro) {
        super("No votes found for restaurant ID: " + restauranteId + " " + "and date: " + registro);
    }
}
