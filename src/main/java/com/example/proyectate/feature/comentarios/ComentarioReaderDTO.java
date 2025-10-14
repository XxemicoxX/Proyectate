package com.example.proyectate.feature.comentarios;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ComentarioReaderDTO(
     Long id,
     String contenido,
     @JsonProperty("fecha_creacion")
     LocalDate fechaCreacion,
     @JsonProperty("usuario_id")
     Long usuarioId,
     @JsonProperty("tarea_id")
     Long tareaId
) {

}
