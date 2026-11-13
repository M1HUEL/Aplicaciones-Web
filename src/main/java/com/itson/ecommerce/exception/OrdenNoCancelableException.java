package com.itson.ecommerce.exception;

import com.itson.ecommerce.entity.EstadoOrden;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class OrdenNoCancelableException extends RuntimeException {

  public OrdenNoCancelableException(Long ordenId, EstadoOrden estado) {
    super("La orden " + ordenId + " no se puede cancelar porque está en estado " + estado + ".");
  }
}
