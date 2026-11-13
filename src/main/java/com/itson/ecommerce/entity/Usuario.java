package com.itson.ecommerce.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
public class Usuario {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 100)
  private String nombre;

  @Column(nullable = false, unique = true, length = 150)
  private String correo;

  @Column(nullable = false, length = 255)
  private String contrasena;

  @Column(length = 20)
  private String telefono;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 20)
  private Rol rol = Rol.CLIENTE;

  @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Carrito> carritos = new ArrayList<>();

  @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Orden> ordenes = new ArrayList<>();

  @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Direccion> direcciones = new ArrayList<>();

  public boolean esAdministrador() {
    return rol == Rol.ADMINISTRADOR;
  }

  public boolean puedeComprar() {
    return rol == Rol.CLIENTE || rol == Rol.ADMINISTRADOR;
  }

  public Direccion getDireccionPredeterminada() {
    return direcciones.stream()
        .filter(Direccion::isPredeterminada)
        .findFirst()
        .orElse(direcciones.isEmpty() ? null : direcciones.get(0));
  }
}
