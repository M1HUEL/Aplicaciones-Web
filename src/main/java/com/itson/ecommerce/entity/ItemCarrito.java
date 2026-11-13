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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "items_carrito")
@Getter
@Setter
@NoArgsConstructor
public class ItemCarrito {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "carrito_id", nullable = false)
  private Carrito carrito;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "formato_libro_id", nullable = false)
  private FormatoLibro formatoLibro;

  @Column(nullable = false)
  private int cantidad;

  @Column(name = "precio_unitario", nullable = false)
  private double precioUnitario;

  public double getSubtotal() {
    return cantidad * precioUnitario;
  }
}
