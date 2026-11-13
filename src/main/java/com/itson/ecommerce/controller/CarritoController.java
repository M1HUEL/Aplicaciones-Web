package com.itson.ecommerce.controller;

import com.itson.ecommerce.dto.ActualizarCantidadRequest;
import com.itson.ecommerce.dto.AgregarItemCarritoRequest;
import com.itson.ecommerce.dto.CarritoResponse;
import com.itson.ecommerce.exception.EndpointNoImplementadoException;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/carrito")
public class CarritoController {

  @GetMapping
  public CarritoResponse obtener() {
    throw new EndpointNoImplementadoException();
  }

  @PostMapping("/items")
  public CarritoResponse agregar(@Valid @RequestBody AgregarItemCarritoRequest solicitud) {
    throw new EndpointNoImplementadoException();
  }

  @PatchMapping("/items/{libroId}")
  public CarritoResponse actualizar(@PathVariable Long libroId, @Valid @RequestBody ActualizarCantidadRequest solicitud) {
    throw new EndpointNoImplementadoException();
  }

  @DeleteMapping("/items/{libroId}")
  public CarritoResponse eliminar(@PathVariable Long libroId) {
    throw new EndpointNoImplementadoException();
  }
}
