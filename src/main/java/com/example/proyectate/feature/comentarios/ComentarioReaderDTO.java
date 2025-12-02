package com.example.proyectate.feature.comentarios;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ComentarioReaderDTO(
     Long id,
     String contenido,
     @JsonProperty("fecha_creacion")
     LocalDateTime fechaCreacion,
     @JsonProperty("usuario_id")
     Long usuarioId,
     @JsonProperty("nombre_usuario")
     String nombreUsuario,
     @JsonProperty("tarea_id")
     Long tareaId
) {}
