package com.example.proyectate.feature.proyectos;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProyectoReaderDTO(
     Long id,
     String nombre,
     String descripcion,
     @JsonProperty("id_usuario")
     Long idUsuario

) {

}
