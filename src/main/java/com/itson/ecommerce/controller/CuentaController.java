package com.itson.ecommerce.controller;

import com.itson.ecommerce.dto.LoginCorreoRequest;
import com.itson.ecommerce.dto.LoginResponse;
import com.itson.ecommerce.dto.MensajeResponse;
import com.itson.ecommerce.dto.RegistroRequest;
import com.itson.ecommerce.dto.UsuarioResponse;
import com.itson.ecommerce.exception.EndpointNoImplementadoException;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class CuentaController {

  @PostMapping("/registro")
  public UsuarioResponse registrar(@Valid @RequestBody RegistroRequest solicitud) {
    throw new EndpointNoImplementadoException();
  }

  @PostMapping("/login")
  public LoginResponse iniciarSesion(@Valid @RequestBody LoginCorreoRequest solicitud) {
    throw new EndpointNoImplementadoException();
  }

  @PostMapping("/logout")
  public MensajeResponse cerrarSesion() {
    throw new EndpointNoImplementadoException();
  }

  @GetMapping("/me")
  public UsuarioResponse actual(Authentication autenticacion) {
    throw new EndpointNoImplementadoException();
  }
}
