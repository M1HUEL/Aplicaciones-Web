package com.itson.ecommerce.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "envios")
@Getter
@Setter
@NoArgsConstructor
public class Envio {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "orden_id", nullable = false, unique = true)
  private Orden orden;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "direccion_id", nullable = false)
  private Direccion direccion;

  @Column(nullable = false, length = 100)
  private String paqueteria;

  @Column(name = "numero_guia", nullable = false, length = 100)
  private String numeroGuia;
}
