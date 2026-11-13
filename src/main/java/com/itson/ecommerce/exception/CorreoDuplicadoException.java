package com.itson.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CorreoDuplicadoException extends RuntimeException {

  public CorreoDuplicadoException(String correo) {
    super("El correo " + correo + " ya está registrado.");
  }
}
