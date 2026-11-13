package com.itson.ecommerce.dto;

import com.itson.ecommerce.entity.EstadoOrden;

import java.time.LocalDateTime;
import java.util.List;

public record OrdenDetalleResponse(
    Long id,
    String numeroOrden,
    LocalDateTime fecha,
    EstadoOrden estado,
    List<ItemOrdenResponse> items,
    double total,
    DatosEntregaResponse entrega) {
}
