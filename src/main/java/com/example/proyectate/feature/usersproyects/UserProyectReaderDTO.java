package com.example.proyectate.feature.usersproyects;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserProyectReaderDTO(
        Long id,
        @JsonProperty("id_usuario") Long user,
        @JsonProperty("id_proyecto") Long proyect,
        String rol) {
}
