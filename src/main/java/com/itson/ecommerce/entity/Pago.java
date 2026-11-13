package com.itson.ecommerce.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pagos")
@Getter
@Setter
@NoArgsConstructor
public class Pago {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "orden_id", nullable = false, unique = true)
  private Orden orden;

  @Column(nullable = false)
  private double monto;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 30)
  private MetodoPago metodo;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 20)
  private EstadoPago estado = EstadoPago.PENDIENTE;

  @Column(nullable = false)
  private LocalDateTime fecha = LocalDateTime.now();
}
