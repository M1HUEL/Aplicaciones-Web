package com.itson.ecommerce.dto;

import java.util.List;

public record LibroResumenResponse(
    Long id,
    String titulo,
    List<String> autores,
    double precio,
    String portada,
    boolean disponible) {
}
