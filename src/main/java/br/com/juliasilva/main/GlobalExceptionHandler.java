package br.com.juliasilva.main;

import br.com.juliasilva.main.exception.EmailNotFoundExceptionOrPasswordWrong;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailNotFoundExceptionOrPasswordWrong.class)
    public ResponseEntity<String> handleEmailNotFoundException(EmailNotFoundExceptionOrPasswordWrong ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

}
