package com.itson.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class CredencialesInvalidasException extends RuntimeException {

  public CredencialesInvalidasException() {
    super("El correo o la contraseña son incorrectos.");
  }
}
