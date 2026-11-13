package com.itson.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class StockInsuficienteException extends RuntimeException {

  public StockInsuficienteException(String mensaje) {
    super(mensaje);
  }

  public StockInsuficienteException(Long libroId, int solicitado, int disponible) {
    super("No hay existencias suficientes del libro " + libroId
        + ": se solicitaron " + solicitado + " y solo hay " + disponible + ".");
  }
}
