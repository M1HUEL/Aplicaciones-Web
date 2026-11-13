package com.itson.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ContrasenasNoCoincidenException extends RuntimeException {

  public ContrasenasNoCoincidenException() {
    super("La contraseña y su confirmación no coinciden.");
  }
}
