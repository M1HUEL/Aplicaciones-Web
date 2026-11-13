package com.itson.ecommerce.dto;

import java.util.List;

public record CarritoResponse(
    List<ItemCarritoResponse> items,
    double subtotal,
    double envio,
    double total) {
}
