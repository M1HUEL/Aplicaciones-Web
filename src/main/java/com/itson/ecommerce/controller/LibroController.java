package com.itson.ecommerce.controller;

import com.itson.ecommerce.dto.LibroDetalleResponse;
import com.itson.ecommerce.dto.LibroResumenResponse;
import com.itson.ecommerce.exception.EndpointNoImplementadoException;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

  @GetMapping
  public List<LibroResumenResponse> listar(
      @RequestParam(required = false) String q,
      @RequestParam(required = false) Long categoria,
      @RequestParam(required = false) Long autor,
      @RequestParam(required = false) String orden) {
    throw new EndpointNoImplementadoException();
  }

  @GetMapping("/destacados")
  public List<LibroResumenResponse> destacados() {
    throw new EndpointNoImplementadoException();
  }

  @GetMapping("/{id}")
  public LibroDetalleResponse detalle(@PathVariable Long id) {
    throw new EndpointNoImplementadoException();
  }
}
