package com.itson.ecommerce.dto;

public record ItemOrdenResponse(
    Long libroId,
    String titulo,
    String formato,
    int cantidad,
    double precioUnitario,
    double subtotal) {
}
