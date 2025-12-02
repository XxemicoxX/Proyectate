package com.example.proyectate.feature.tareas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface TareaRepository extends JpaRepository<Tarea, Long> {
    List<Tarea> findByTitulo(String titulo);
    List<Tarea> findByPrioridad(String prioridad);
    List<Tarea> findByProyectoId(Long proyectoId);
}
