package com.example.proyectate.feature.usersproyects;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserProyectDetailDTO(
        Long id,
        @JsonProperty("id_usuario") Long idUsuario,
        @JsonProperty("id_proyecto") Long idProyecto,
        String rol,
        @JsonProperty("nombre_usuario") String nombreUsuario,
        @JsonProperty("email_usuario") String emailUsuario) {

}
