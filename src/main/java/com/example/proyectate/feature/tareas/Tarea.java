package com.example.proyectate.feature.tareas;

import com.example.proyectate.feature.etiquetas.Etiqueta;
import com.example.proyectate.feature.proyectos.Proyecto;

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
public class Tarea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tarea")
    private Long id;
    private String titulo;
    private String descripcion;
    private String prioridad;
    private String estado;
    @ManyToOne
    @JoinColumn (name = "id_proyecto")
    private Proyecto proyecto;
    @ManyToOne
    @JoinColumn (name = "id_etiqueta")
    private Etiqueta etiqueta;
}
