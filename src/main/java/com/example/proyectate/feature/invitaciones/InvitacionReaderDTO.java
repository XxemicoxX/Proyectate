package com.example.proyectate.feature.invitaciones;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public record InvitacionReaderDTO(
    Long id,
    
    @JsonProperty("id_proyecto") 
    Long idProyecto,
    
    @JsonProperty("nombre_proyecto") 
    String nombreProyecto,
    
    @JsonProperty("descripcion_proyecto") 
    String descripcionProyecto,
    
    @JsonProperty("id_usuario_invitado") 
    Long idUsuarioInvitado,
    
    @JsonProperty("email_invitado") 
    String emailInvitado,
    
    @JsonProperty("nombre_invitado") 
    String nombreInvitado,
    
    @JsonProperty("id_usuario_invitador") 
    Long idUsuarioInvitador,
    
    @JsonProperty("nombre_invitador") 
    String nombreInvitador,
    
    @JsonProperty("rol_asignado") 
    String rolAsignado,
    
    String estado,
    
    @JsonProperty("fecha_invitacion") 
    LocalDateTime fechaInvitacion,
    
    @JsonProperty("fecha_respuesta") 
    LocalDateTime fechaRespuesta
) {
    
}
