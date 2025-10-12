package com.example.proyectate.feature.usuarios;

import com.example.proyectate.util.RolSistema;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioWriterDTO(
        Long id,
        @NotBlank(message = "Debes ingresar el nombre")
        String nombre,
        @Email(message = "Correo invalido")
        String email,
        @NotBlank(message = "Debes ingresar una contraseña")
        String contrasena,
        RolSistema rol

) {

}
