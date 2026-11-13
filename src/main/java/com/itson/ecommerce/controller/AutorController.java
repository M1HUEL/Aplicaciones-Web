package com.itson.ecommerce.controller;

import com.itson.ecommerce.dto.AutorResponse;
import com.itson.ecommerce.exception.EndpointNoImplementadoException;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/autores")
public class AutorController {

  @GetMapping
  public List<AutorResponse> listar() {
    throw new EndpointNoImplementadoException();
  }
}
