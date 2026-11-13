package com.itson.ecommerce.controller;

import com.itson.ecommerce.dto.LoginRequest;
import com.itson.ecommerce.dto.LoginResponse;
import com.itson.ecommerce.service.AuthService;
import jakarta.validation.Valid;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  private final AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping("/iniciar-sesion")
  public LoginResponse iniciarSesion(@Valid @RequestBody LoginRequest solicitud) {
    return authService.iniciarSesion(solicitud);
  }

  @GetMapping("/perfil")
  public Map<String, Object> perfil(Authentication autenticacion) {
    return authService.obtenerPerfil(autenticacion);
  }

  @ExceptionHandler(BadCredentialsException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public Map<String, String> credencialesInvalidas(BadCredentialsException excepcion) {
    return Map.of("error", "Credenciales inválidas");
  }
}
