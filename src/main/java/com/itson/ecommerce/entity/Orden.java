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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ordenes")
@Getter
@Setter
@NoArgsConstructor
public class Orden {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "numero_orden", nullable = false, unique = true, length = 50)
  private String numeroOrden;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "usuario_id", nullable = false)
  private Usuario usuario;

  @Column(nullable = false)
  private LocalDateTime fecha = LocalDateTime.now();

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 20)
  private EstadoOrden estado = EstadoOrden.PENDIENTE;

  @OneToOne(mappedBy = "orden", cascade = CascadeType.ALL, orphanRemoval = true)
  private Envio envio;

  @OneToOne(mappedBy = "orden", cascade = CascadeType.ALL, orphanRemoval = true)
  private Pago pago;

  @Column(name = "pago_procesado", nullable = false)
  private boolean pagoProcesado;

  @OneToMany(mappedBy = "orden", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<ItemOrden> items = new ArrayList<>();

  public double getTotal() {
    return items.stream().mapToDouble(ItemOrden::getSubtotal).sum();
  }
}
