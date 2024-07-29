package br.com.thiagofspaiva.urbanape.exceptions;

public class DataAlreadyExistsException extends RuntimeException {
    public DataAlreadyExistsException(String message){
        super(message);
    }
}
