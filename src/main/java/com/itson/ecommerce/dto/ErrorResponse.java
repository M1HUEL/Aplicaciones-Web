package com.itson.ecommerce.dto;

import java.util.Map;

public record ErrorResponse(int status, String mensaje, Map<String, String> errores) {
}
