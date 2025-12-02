package com.example.proyectate.feature.invitaciones;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.proyectate.util.EstadoInvitacion;

public interface InvitacionRepository extends JpaRepository<Invitacion, Long> {
    
    // Buscar invitaciones pendientes de un usuario
    List<Invitacion> findByUsuarioInvitadoIdAndEstado(Long usuarioId, EstadoInvitacion estado);
    
    // Buscar todas las invitaciones de un proyecto
    List<Invitacion> findByProyectoId(Long proyectoId);
    
    // Buscar invitaciones enviadas por un usuario
    List<Invitacion> findByUsuarioInvitadorId(Long usuarioId);
    
    // Verificar si existe una invitación pendiente
    Optional<Invitacion> findByProyectoIdAndUsuarioInvitadoIdAndEstado(
        Long proyectoId, 
        Long usuarioId, 
        EstadoInvitacion estado
    );
    
    // Buscar todas las invitaciones de un usuario (enviadas y recibidas)
    @Query("SELECT i FROM Invitacion i WHERE i.usuarioInvitado.id = :usuarioId OR i.usuarioInvitador.id = :usuarioId")
    List<Invitacion> findAllByUsuarioId(Long usuarioId);
}
