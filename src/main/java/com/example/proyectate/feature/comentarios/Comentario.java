package com.example.proyectate.feature.comentarios;

import java.time.LocalDate;

import com.example.proyectate.feature.tareas.Tarea;
import com.example.proyectate.feature.usuarios.Usuarios;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comentario")
    private Long id;
    @Column(nullable = false, length = 150)
    private String contenido;
    private LocalDate fecha_creacion;
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuarios usuario;
    @ManyToOne
    @JoinColumn(name = "id_tarea")
    private Tarea tarea;
}
