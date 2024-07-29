package br.com.thiagofspaiva.urbanape.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message){
        super(message);
    }
}
