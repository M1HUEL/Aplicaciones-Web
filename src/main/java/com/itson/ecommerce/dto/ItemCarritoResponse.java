package com.itson.ecommerce.dto;

public record ItemCarritoResponse(
    Long libroId,
    String titulo,
    String formato,
    int cantidad,
    double precioUnitario,
    double subtotal) {
}
