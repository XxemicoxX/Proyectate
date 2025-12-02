package com.example.proyectate.feature.tareas;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TareaWriterDTO(
        Long id,
        @NotBlank(message = "Debe tener un titulo")
        String titulo,
        String descripcion, // Quitar @NotBlank para permitir descripciones vacías
        @NotBlank(message = "La prioridad de la tarea es necesaria")
        String prioridad,
        @NotBlank
        String estado,
        @JsonProperty("id_proyecto")
        @NotNull(message = "El proyecto es obligatorio")
        Long idProyecto,
        @JsonProperty("id_etiqueta")
        Long idEtiqueta, // Opcional - sin validación
        @JsonProperty("id_usuario")
        Long idUsuario // Opcional - sin validación
) {
}
