package com.example.proyectate.feature.invitaciones;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.proyectate.feature.proyectos.Proyecto;
import com.example.proyectate.feature.proyectos.ProyectoRepository;
import com.example.proyectate.feature.users.User;
import com.example.proyectate.feature.users.UserRepository;
import com.example.proyectate.feature.usersproyects.UserProyect;
import com.example.proyectate.feature.usersproyects.UserProyectRepository;
import com.example.proyectate.util.EstadoInvitacion;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InvitacionService {
    private final InvitacionRepository invitacionRepository;
    private final UserRepository userRepository;
    private final ProyectoRepository proyectoRepository;
    private final UserProyectRepository userProyectRepository;
    private final InvitacionMapper mapper;

    @Transactional
    public InvitacionReaderDTO enviarInvitacion(InvitacionWriterDTO dto, Long idUsuarioInvitador) {
        // Verificar que el proyecto existe
        Proyecto proyecto = proyectoRepository.findById(dto.idProyecto())
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));

        // Buscar usuario por email
        User usuarioInvitado = userRepository.findByEmail(dto.emailInvitado())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con el email: " + dto.emailInvitado()));

        // Verificar que no se invite a sí mismo
        if (usuarioInvitado.getId().equals(idUsuarioInvitador)) {
            throw new RuntimeException("No puedes invitarte a ti mismo");
        }

        // Verificar que no exista una invitación pendiente
        Optional<Invitacion> invitacionExistente = invitacionRepository
                .findByProyectoIdAndUsuarioInvitadoIdAndEstado(
                        dto.idProyecto(),
                        usuarioInvitado.getId(),
                        EstadoInvitacion.PENDIENTE);

        if (invitacionExistente.isPresent()) {
            throw new RuntimeException("Ya existe una invitación pendiente para este usuario en este proyecto");
        }

        // Verificar que el usuario no esté ya en el proyecto
        boolean yaEstaEnProyecto = userProyectRepository
                .existsByUser_IdAndProyect_Id(usuarioInvitado.getId(), dto.idProyecto());

        if (yaEstaEnProyecto) {
            throw new RuntimeException("El usuario ya es miembro de este proyecto");
        }

        // Crear la invitación
        User usuarioInvitador = userRepository.findById(idUsuarioInvitador)
                .orElseThrow(() -> new RuntimeException("Usuario invitador no encontrado"));

        Invitacion invitacion = Invitacion.builder()
                .proyecto(proyecto)
                .usuarioInvitado(usuarioInvitado)
                .usuarioInvitador(usuarioInvitador)
                .rolAsignado(dto.rolAsignado())
                .estado(EstadoInvitacion.PENDIENTE)
                .fechaInvitacion(LocalDateTime.now())
                .build();

        Invitacion saved = invitacionRepository.save(invitacion);
        return mapper.toDto(saved);
    }

    @Transactional
    public InvitacionReaderDTO responderInvitacion(Long idInvitacion, boolean aceptar, Long idUsuario) {
        Invitacion invitacion = invitacionRepository.findById(idInvitacion)
                .orElseThrow(() -> new RuntimeException("Invitación no encontrada"));

        // Verificar que el usuario sea el invitado
        if (!invitacion.getUsuarioInvitado().getId().equals(idUsuario)) {
            throw new RuntimeException("No tienes permiso para responder esta invitación");
        }

        // Verificar que esté pendiente
        if (!invitacion.getEstado().equals(EstadoInvitacion.PENDIENTE)) {
            throw new RuntimeException("Esta invitación ya fue respondida");
        }

        // Actualizar estado
        invitacion.setEstado(aceptar ? EstadoInvitacion.ACEPTADA : EstadoInvitacion.RECHAZADA);
        invitacion.setFechaRespuesta(LocalDateTime.now());

        // Si acepta, agregar a usuarios_proyectos
        if (aceptar) {
            UserProyect userProyect = UserProyect.builder()
                    .user(invitacion.getUsuarioInvitado())
                    .proyect(invitacion.getProyecto())
                    .rol(invitacion.getRolAsignado())
                    .build();

            userProyectRepository.save(userProyect);
        }

        Invitacion saved = invitacionRepository.save(invitacion);
        return mapper.toDto(saved);
    }

    public List<InvitacionReaderDTO> getInvitacionesPendientes(Long idUsuario) {
        return invitacionRepository
                .findByUsuarioInvitadoIdAndEstado(idUsuario, EstadoInvitacion.PENDIENTE)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    public List<InvitacionReaderDTO> getInvitacionesEnviadas(Long idUsuario) {
        return invitacionRepository
                .findByUsuarioInvitadorId(idUsuario)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    public List<InvitacionReaderDTO> getInvitacionesPorProyecto(Long idProyecto) {
        return invitacionRepository
                .findByProyectoId(idProyecto)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Transactional
    public void cancelarInvitacion(Long idInvitacion, Long idUsuario) {
        Invitacion invitacion = invitacionRepository.findById(idInvitacion)
                .orElseThrow(() -> new RuntimeException("Invitación no encontrada"));

        // Solo el invitador puede cancelar
        if (!invitacion.getUsuarioInvitador().getId().equals(idUsuario)) {
            throw new RuntimeException("No tienes permiso para cancelar esta invitación");
        }

        // Solo se pueden cancelar invitaciones pendientes
        if (!invitacion.getEstado().equals(EstadoInvitacion.PENDIENTE)) {
            throw new RuntimeException("Solo se pueden cancelar invitaciones pendientes");
        }

        invitacionRepository.delete(invitacion);
    }

    public long contarInvitacionesPendientes(Long idUsuario) {
        return invitacionRepository
                .findByUsuarioInvitadoIdAndEstado(idUsuario, EstadoInvitacion.PENDIENTE)
                .size();
    }
}
