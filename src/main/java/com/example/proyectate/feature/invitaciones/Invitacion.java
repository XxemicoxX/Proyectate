package com.example.proyectate.feature.invitaciones;

import java.time.LocalDateTime;

import com.example.proyectate.feature.proyectos.Proyecto;
import com.example.proyectate.feature.users.User;
import com.example.proyectate.util.EstadoInvitacion;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Invitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_invitacion")
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "id_proyecto", nullable = false)
    private Proyecto proyecto;
    
    @ManyToOne
    @JoinColumn(name = "id_usuario_invitado", nullable = false)
    private User usuarioInvitado;
    
    @ManyToOne
    @JoinColumn(name = "id_usuario_invitador", nullable = false)
    private User usuarioInvitador;
    
    @Column(name = "rol_asignado", nullable = false, length = 50)
    private String rolAsignado;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoInvitacion estado;
    
    @Column(name = "fecha_invitacion", nullable = false)
    private LocalDateTime fechaInvitacion;
    
    @Column(name = "fecha_respuesta")
    private LocalDateTime fechaRespuesta;
}
