package com.example.proyectate.feature.invitaciones;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record InvitacionWriterDTO(
    Long id,
    
    @NotNull(message = "El ID del proyecto es obligatorio")
    @JsonProperty("id_proyecto") 
    Long idProyecto,
    
    @NotBlank(message = "El email del usuario es obligatorio")
    @Email(message = "Debe ser un email válido")
    @JsonProperty("email_invitado") 
    String emailInvitado,
    
    @NotBlank(message = "El rol es obligatorio")
    @JsonProperty("rol_asignado") 
    String rolAsignado) {
    
}
