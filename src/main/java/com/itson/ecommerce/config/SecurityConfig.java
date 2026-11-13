package com.itson.ecommerce.config;

import com.itson.ecommerce.security.JwtAuthenticationFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private final JwtAuthenticationFilter jwtAuthenticationFilter;

  public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
    this.jwtAuthenticationFilter = jwtAuthenticationFilter;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
      .csrf(csrf -> csrf.disable())
      .sessionManagement(sesion ->
        sesion.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
      .authorizeHttpRequests(autorizacion -> autorizacion
        .requestMatchers(HttpMethod.POST, "/api/auth/iniciar-sesion").permitAll()
        .requestMatchers("/error").permitAll()
        .anyRequest().authenticated())
      .exceptionHandling(excepciones -> excepciones
        .authenticationEntryPoint((solicitud, respuesta, excepcion) -> {
          respuesta.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
          respuesta.setContentType(MediaType.APPLICATION_JSON_VALUE);
          respuesta.getWriter().write("{\"error\":\"No autorizado\"}");
        }))
      .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
    UserDetails usuario = User.withUsername("admin")
      .password(passwordEncoder.encode("admin123"))
      .roles("ADMIN")
      .build();

    UserDetails cliente = User.withUsername("cliente")
      .password(passwordEncoder.encode("cliente123"))
      .roles("CLIENTE")
      .build();

    return new InMemoryUserDetailsManager(usuario, cliente);
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration configuracion) throws Exception {
    return configuracion.getAuthenticationManager();
  }
}
