package com.itson.ecommerce.entity;

import jakarta.persistence.CascadeType;
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
@Table(name = "items_orden")
@Getter
@Setter
@NoArgsConstructor
public class ItemOrden {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "orden_id", nullable = false)
  private Orden orden;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "formato_libro_id", nullable = false)
  private FormatoLibro formatoLibro;

  @Column(nullable = false)
  private int cantidad;

  @Column(name = "precio_unitario", nullable = false)
  private double precioUnitario;

  @OneToOne(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
  private Descarga descarga;

  public boolean esDigital() {
    return formatoLibro != null && formatoLibro.esDigital();
  }

  public double getSubtotal() {
    return cantidad * precioUnitario;
  }
}
