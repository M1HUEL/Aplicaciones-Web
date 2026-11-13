package com.itson.ecommerce.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegistroRequest(
    @NotBlank(message = "El nombre es obligatorio") String nombre,
    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El correo no tiene un formato válido") String correo,
    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres") String contrasena,
    @NotBlank(message = "La confirmación de la contraseña es obligatoria") String confirmar) {
}
