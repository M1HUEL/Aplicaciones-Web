package com.itson.ecommerce.dto;

public record LoginResponse(String token, String tipo, long expiracion) {
}
