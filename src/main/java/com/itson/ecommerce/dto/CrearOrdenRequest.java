package com.itson.ecommerce.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record CrearOrdenRequest(
    @NotBlank(message = "El nombre es obligatorio") String nombre,
    @NotBlank(message = "La dirección es obligatoria") String direccion,
    @NotBlank(message = "La ciudad es obligatoria") String ciudad,
    @NotBlank(message = "El teléfono es obligatorio") String telefono,
    List<@Valid ItemOrdenRequest> items) {
}
