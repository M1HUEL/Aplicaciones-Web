package com.itson.ecommerce.security;

import com.itson.ecommerce.config.JwtConfig;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtConfig jwtConfig;

  public JwtAuthenticationFilter(JwtConfig jwtConfig) {
    this.jwtConfig = jwtConfig;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest solicitud, HttpServletResponse respuesta, FilterChain cadena) throws ServletException, IOException {
    String cabecera = solicitud.getHeader(HttpHeaders.AUTHORIZATION);

    if (cabecera != null && cabecera.startsWith("Bearer ") && SecurityContextHolder.getContext().getAuthentication() == null) {
      String token = cabecera.substring(7);

      if (jwtConfig.esValido(token)) {
        String usuario = jwtConfig.extraerUsuario(token);
        List<SimpleGrantedAuthority> autoridades = jwtConfig.extraerRoles(token).stream()
          .map(SimpleGrantedAuthority::new)
          .toList();

        UsernamePasswordAuthenticationToken autenticacion = new UsernamePasswordAuthenticationToken(usuario, null, autoridades);
        autenticacion.setDetails(new WebAuthenticationDetailsSource().buildDetails(solicitud));
        SecurityContextHolder.getContext().setAuthentication(autenticacion);
      }
    }

    cadena.doFilter(solicitud, respuesta);
  }
}
