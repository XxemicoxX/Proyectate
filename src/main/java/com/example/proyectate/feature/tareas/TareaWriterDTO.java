package com.example.proyectate.feature.tareas;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

public record TareaWriterDTO(
        Long id,
        @NotBlank(message = "Debe tener un titulo")
        String titulo,
        @NotBlank(message = "La descripcion es obligatoria")
        String descripcion,
        @NotBlank(message = "La prioridad de la tarea es necesaria")
        String prioridad,
        @NotBlank
        String estado,
        @JsonProperty("id_proyecto")
        Long idProyecto,
        @JsonProperty("id_etiqueta")
        Long idEtiqueta,
        @JsonProperty("id_usuario")
        Long idUsuario) {

}
