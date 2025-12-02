package com.example.proyectate.feature.comentarios;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.proyectate.feature.tareas.Tarea;
import com.example.proyectate.feature.users.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comentario")
    private Long id;
    
    @Column(nullable = false, length = 150)
    private String contenido;
    
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion; // Cambiado a LocalDateTime para incluir hora
    
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private User usuario;
    
    @ManyToOne
    @JoinColumn(name = "id_tarea")
    private Tarea tarea;
}
