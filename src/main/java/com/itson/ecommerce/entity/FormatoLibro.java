package com.itson.ecommerce.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "formatos_libro")
@Getter
@Setter
@NoArgsConstructor
public class FormatoLibro {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "libro_id", nullable = false)
  private Libro libro;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 20)
  private Formato formato;

  @Column(nullable = false)
  private double precio;

  @OneToOne(mappedBy = "formatoLibro", cascade = CascadeType.ALL, orphanRemoval = true)
  private ItemStock stock;

  public boolean esDigital() {
    return formato == Formato.DIGITAL;
  }
}
