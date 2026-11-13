package com.itson.ecommerce.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtConfig {

  private final SecretKey clave;
  private final long expiracionMs;

  public JwtConfig(
    @Value("${jwt.secreto}") String secreto,
    @Value("${jwt.expiracion-ms}") long expiracionMs) {
    this.clave = Keys.hmacShaKeyFor(secreto.getBytes(StandardCharsets.UTF_8));
    this.expiracionMs = expiracionMs;
  }

  public String generarToken(UserDetails usuario) {
    List<String> roles = usuario.getAuthorities().stream()
      .map(GrantedAuthority::getAuthority)
      .toList();
    Date ahora = new Date();
    return Jwts.builder()
      .subject(usuario.getUsername())
      .claim("roles", roles)
      .issuedAt(ahora)
      .expiration(new Date(ahora.getTime() + expiracionMs))
      .signWith(clave)
      .compact();
  }

  public Claims extraerClaims(String token) {
    return Jwts.parser()
      .verifyWith(clave)
      .build()
      .parseSignedClaims(token)
      .getPayload();
  }

  public String extraerUsuario(String token) {
    return extraerClaims(token).getSubject();
  }

  @SuppressWarnings("unchecked")
  public List<String> extraerRoles(String token) {
    List<String> roles = extraerClaims(token).get("roles", List.class);
    return roles != null ? roles : List.of();
  }

  public boolean esValido(String token) {
    try {
      extraerClaims(token);
      return true;
    } catch (Exception excepcion) {
      return false;
    }
  }

  public long getExpiracionMs() {
    return expiracionMs;
  }
}
