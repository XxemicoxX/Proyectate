package com.example.proyectate.feature.comentarios;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ComentarioWriterDTO(
     Long id,
     @NotBlank(message = "El contenido es obligatorio")
     String contenido,
     @JsonProperty("fecha_creacion")
     LocalDate fechaCreacion,
     @NotNull
     @JsonProperty("usuario_id")
     Long usuarioId,
     @NotNull
     @JsonProperty("tarea_id")
     Long tareaId
) {

}
