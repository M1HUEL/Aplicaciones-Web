package com.itson.ecommerce.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ItemOrdenRequest(
    @NotNull(message = "El libro es obligatorio") Long libroId,
    @Min(value = 1, message = "La cantidad debe ser mayor a 0") int cantidad) {
}
