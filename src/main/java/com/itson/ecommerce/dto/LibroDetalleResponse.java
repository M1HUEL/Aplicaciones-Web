package com.itson.ecommerce.dto;

import java.util.List;

public record LibroDetalleResponse(
    Long id,
    String titulo,
    List<String> autores,
    String editorial,
    String categoria,
    String isbn,
    double precio,
    String descripcion,
    String portada,
    boolean disponible) {
}
