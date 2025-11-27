package com.example.proyectate.feature.proyectos;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProyectoReaderDTO(
     Long id,
     String nombre,
     String descripcion,
     LocalDate fechaInicio,
     String estado,
     @JsonProperty("id_usuario")
     Long idUsuario

) {

}
