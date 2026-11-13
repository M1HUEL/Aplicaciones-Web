package com.itson.ecommerce.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ajustes_inventario")
@Getter
@Setter
@NoArgsConstructor
public class AjusteInventario {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "stock_id", nullable = false)
  private ItemStock stock;

  @Column(nullable = false)
  private int cantidad;

  @Column(length = 255)
  private String motivo;

  @Column(nullable = false)
  private LocalDateTime fecha = LocalDateTime.now();
}
