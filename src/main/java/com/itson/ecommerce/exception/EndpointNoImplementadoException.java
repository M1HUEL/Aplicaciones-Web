package com.itson.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
public class EndpointNoImplementadoException extends RuntimeException {

  public EndpointNoImplementadoException() {
    super("Endpoint definido pero aún no implementado.");
  }
}
