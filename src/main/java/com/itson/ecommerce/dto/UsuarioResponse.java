package com.itson.ecommerce.dto;

import com.itson.ecommerce.entity.Rol;

public record UsuarioResponse(
    Long id,
    String nombre,
    String correo,
    String telefono,
    Rol rol) {
}
