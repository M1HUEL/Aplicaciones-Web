package com.itson.ecommerce.controller;

import com.itson.ecommerce.dto.CategoriaResponse;
import com.itson.ecommerce.exception.EndpointNoImplementadoException;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

  @GetMapping
  public List<CategoriaResponse> listar() {
    throw new EndpointNoImplementadoException();
  }
}
