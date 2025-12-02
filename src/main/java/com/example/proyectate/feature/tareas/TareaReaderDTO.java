package com.example.proyectate.feature.tareas;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TareaReaderDTO(
          Long id,
          String titulo,
          String descripcion,
          String prioridad,
          String estado,
          @JsonProperty("id_proyecto") Long idProyecto,
          @JsonProperty("id_etiqueta") Long idEtiqueta,
          @JsonProperty("id_usuario") Long idUsuario) {
}
