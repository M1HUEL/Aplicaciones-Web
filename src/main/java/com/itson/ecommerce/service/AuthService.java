package com.itson.ecommerce.service;

import com.itson.ecommerce.config.JwtConfig;
import com.itson.ecommerce.dto.LoginRequest;
import com.itson.ecommerce.dto.LoginResponse;

import java.util.List;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

  private final AuthenticationManager authenticationManager;
  private final JwtConfig jwtConfig;

  public AuthService(AuthenticationManager authenticationManager, JwtConfig jwtConfig) {
    this.authenticationManager = authenticationManager;
    this.jwtConfig = jwtConfig;
  }

  public LoginResponse iniciarSesion(LoginRequest solicitud) {
    Authentication autenticacion = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(solicitud.usuario(), solicitud.contrasena()));

    UserDetails usuario = (UserDetails) autenticacion.getPrincipal();
    String token = jwtConfig.generarToken(usuario);

    return new LoginResponse(token, "Bearer", jwtConfig.getExpiracionMs());
  }

  public Map<String, Object> obtenerPerfil(Authentication autenticacion) {
    List<String> roles = autenticacion.getAuthorities().stream()
      .map(GrantedAuthority::getAuthority)
      .toList();

    return Map.of(
      "usuario", autenticacion.getName(),
      "roles", roles);
  }
}
