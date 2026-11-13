package com.itson.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CarritoVacioException extends RuntimeException {

  public CarritoVacioException() {
    super("No se puede crear una orden sin productos en el carrito.");
  }
}
