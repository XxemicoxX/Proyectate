package com.example.proyectate.feature.proyectos;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProyectoWriterDTO(
     Long id,
     @NotBlank(message = "Debe tener un nombre el proyecto")
     String nombre,
     @NotBlank(message = "Debe tener una descripcion")
     String descripcion,
     LocalDate fechaInicio,
     String estado,
     @NotNull
     @JsonProperty("id_usuario")
     Long idUsuario
) {

}
