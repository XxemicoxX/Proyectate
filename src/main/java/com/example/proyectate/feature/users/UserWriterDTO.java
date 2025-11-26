package com.example.proyectate.feature.users;

import com.example.proyectate.util.RolSistema;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserWriterDTO(
        Long id,
        @NotBlank(message = "Debes ingresar el nombre")
        String name,
        @Email(message = "Correo invalido")
        String email,
        @NotBlank(message = "Debes ingresar una contraseña")
        String password,
        RolSistema rol

) {

}
