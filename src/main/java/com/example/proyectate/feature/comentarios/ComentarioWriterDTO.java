package com.example.proyectate.feature.comentarios;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ComentarioWriterDTO(
     Long id,
     @NotBlank(message = "El contenido es obligatorio")
     String contenido,
     @JsonProperty("tarea_id")
     @NotNull(message = "El ID de la tarea es obligatorio")
     Long tareaId
) {}
