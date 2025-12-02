package com.example.proyectate.feature.usersproyects;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserProyectWriterDTO(
        Long id,
        @JsonProperty("id_usuario")
        @NotNull(message = "El ID del usuario es obligatorio") 
        Long user,
        @JsonProperty("id_proyecto")
        @NotNull(message = "El ID del proyecto es obligatorio") 
        Long proyect,
        @NotBlank(message = "El rol es obligatorio") 
        String rol) {
}
