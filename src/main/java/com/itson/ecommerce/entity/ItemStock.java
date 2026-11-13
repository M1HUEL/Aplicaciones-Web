package com.itson.ecommerce.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "items_stock")
@Getter
@Setter
@NoArgsConstructor
public class ItemStock {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "formato_libro_id", nullable = false, unique = true)
  private FormatoLibro formatoLibro;

  @Column(nullable = false)
  private int cantidad;

  @Column(name = "stock_minimo", nullable = false)
  private int stockMinimo;

  @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<AjusteInventario> ajustes = new ArrayList<>();

  public boolean hayDisponibilidad(int cantidad) {
    return cantidad > 0 && this.cantidad >= cantidad;
  }

  public boolean estaEnStockBajo() {
    return cantidad <= stockMinimo;
  }
}
