package br.com.juliasilva.main.exception;

public class EmailNotFoundExceptionOrPasswordWrong extends RuntimeException {
  public EmailNotFoundExceptionOrPasswordWrong() {
    super("Email not found or wrong password");
  }
}
