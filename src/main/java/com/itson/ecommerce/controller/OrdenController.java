package com.itson.ecommerce.controller;

import com.itson.ecommerce.dto.CrearOrdenRequest;
import com.itson.ecommerce.dto.OrdenDetalleResponse;
import com.itson.ecommerce.dto.OrdenResumenResponse;
import com.itson.ecommerce.exception.EndpointNoImplementadoException;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ordenes")
public class OrdenController {

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public OrdenDetalleResponse crear(@Valid @RequestBody CrearOrdenRequest solicitud) {
    throw new EndpointNoImplementadoException();
  }

  @GetMapping("/mias")
  public List<OrdenResumenResponse> mias() {
    throw new EndpointNoImplementadoException();
  }

  @GetMapping("/{id}")
  public OrdenDetalleResponse detalle(@PathVariable Long id) {
    throw new EndpointNoImplementadoException();
  }

  @PostMapping("/{id}/cancelar")
  public OrdenDetalleResponse cancelar(@PathVariable Long id) {
    throw new EndpointNoImplementadoException();
  }
}
