package com.itson.ecommerce.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "libros")
@Getter
@Setter
@NoArgsConstructor
public class Libro {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 200)
  private String titulo;

  @Column(unique = true, length = 20)
  private String isbn;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "categoria_id")
  private Categoria categoria;

  @Column(columnDefinition = "TEXT")
  private String sinopsis;

  @Column(length = 500)
  private String portada;

  @Column(nullable = false)
  private int paginas;

  @Column(name = "fecha_publicacion")
  private LocalDate fechaPublicacion;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "editorial_id")
  private Editorial editorial;

  @ManyToMany
  @JoinTable(
      name = "libro_autor",
      joinColumns = @JoinColumn(name = "libro_id"),
      inverseJoinColumns = @JoinColumn(name = "autor_id"))
  private List<Autor> autores = new ArrayList<>();

  @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<FormatoLibro> formatos = new ArrayList<>();

  @Column(nullable = false)
  private boolean activo = true;

  public FormatoLibro getFormato(Formato formato) {
    return formatos.stream()
        .filter(formatoLibro -> formatoLibro.getFormato() == formato)
        .findFirst()
        .orElse(null);
  }

  public List<ItemStock> getStocks() {
    return formatos.stream()
        .map(FormatoLibro::getStock)
        .filter(stock -> stock != null)
        .toList();
  }
}
