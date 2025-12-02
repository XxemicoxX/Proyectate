package com.example.proyectate.feature.invitaciones;

import org.springframework.stereotype.Component;

@Component
public class InvitacionMapper {
    public InvitacionReaderDTO toDto(Invitacion entity) {
        return new InvitacionReaderDTO(
            entity.getId(),
            entity.getProyecto().getId(),
            entity.getProyecto().getNombre(),
            entity.getProyecto().getDescripcion(),
            entity.getUsuarioInvitado().getId(),
            entity.getUsuarioInvitado().getEmail(),
            entity.getUsuarioInvitado().getName(),
            entity.getUsuarioInvitador().getId(),
            entity.getUsuarioInvitador().getName(),
            entity.getRolAsignado(),
            entity.getEstado().name(),
            entity.getFechaInvitacion(),
            entity.getFechaRespuesta()
        );
    }
}
