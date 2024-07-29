package br.com.thiagofspaiva.urbanape.exceptions;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice()
public class ErrorMessageHandler {
    private MessageSource messageSource;

    public ErrorMessageHandler(MessageSource message){
        this.messageSource = message;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErroMessageDTO>> MethodArgumentNotValidException(MethodArgumentNotValidException e){
        List<ErroMessageDTO> dto = new ArrayList<>();

        e.getBindingResult().getFieldErrors().forEach(err -> {
            String message = messageSource.getMessage(err, LocaleContextHolder.getLocale());
            ErroMessageDTO error = new ErroMessageDTO(message, err.getField());
            dto.add(error);
        });

        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErroMessageDTO> notFoundException(NotFoundException n) {
        ErroMessageDTO error = new ErroMessageDTO(n.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(DataAlreadyExistsException.class)
    public ResponseEntity<ErroMessageDTO> notFoundException(DataAlreadyExistsException n) {
        ErroMessageDTO error = new ErroMessageDTO(n.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErroMessageDTO> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException m) {
        String errorMessage = String.format("O valor '%s' não é do tipo '%s'", m.getValue(),m.getRequiredType().getSimpleName());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErroMessageDTO(errorMessage));
    }


}
