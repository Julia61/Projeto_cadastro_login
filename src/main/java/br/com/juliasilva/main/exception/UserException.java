package br.com.juliasilva.main.exception;

public class UserException extends RuntimeException{

    public UserException() {
        super("Usuário já existe");
    }
}
