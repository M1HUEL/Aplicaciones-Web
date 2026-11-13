package com.itson.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OrdenNoEncontradaException extends RuntimeException {

  public OrdenNoEncontradaException(Long ordenId) {
    super("No existe la orden con id " + ordenId + ".");
  }
}
