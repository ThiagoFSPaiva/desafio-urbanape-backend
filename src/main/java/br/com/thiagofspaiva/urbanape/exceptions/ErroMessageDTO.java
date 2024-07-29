package br.com.thiagofspaiva.urbanape.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErroMessageDTO {

    private String message;
    private String field;

    public ErroMessageDTO(String message, String field) {
        this.message = message;
        this.field = field;
    }

    public ErroMessageDTO(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
