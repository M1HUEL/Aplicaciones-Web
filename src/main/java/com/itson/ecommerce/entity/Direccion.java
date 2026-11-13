package com.itson.ecommerce.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "direcciones")
@Getter
@Setter
@NoArgsConstructor
public class Direccion {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "usuario_id", nullable = false)
  private Usuario usuario;

  @Column(nullable = false, length = 150)
  private String calle;

  @Column(length = 20)
  private String numero;

  @Column(length = 100)
  private String colonia;

  @Column(nullable = false, length = 100)
  private String ciudad;

  @Column(length = 100)
  private String estado;

  @Column(name = "codigo_postal", length = 10)
  private String codigoPostal;

  @Column(length = 100)
  private String pais;

  @Column(nullable = false)
  private boolean predeterminada;

  @OneToMany(mappedBy = "direccion")
  private List<Envio> envios = new ArrayList<>();

  public String getCompleta() {
    return String.join(" ", Stream.of(calle, numero, colonia, ciudad, estado, codigoPostal, pais)
        .filter(parte -> parte != null && !parte.isBlank())
        .toList());
  }
}
