package com.itson.ecommerce.dto;

import com.itson.ecommerce.entity.EstadoOrden;

import java.time.LocalDateTime;
import java.util.List;

public record OrdenResumenResponse(
    Long id,
    String numeroOrden,
    LocalDateTime fecha,
    List<String> productos,
    EstadoOrden estado,
    double total) {
}
