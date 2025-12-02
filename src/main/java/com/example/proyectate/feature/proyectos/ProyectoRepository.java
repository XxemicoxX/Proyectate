package com.example.proyectate.feature.proyectos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {

    Long countByEstado(String estado);

    @Query("SELECT p FROM Proyecto p ORDER BY p.fechaInicio DESC")
    List<Proyecto> findTop10ByOrderByFechaInicioDesc();

}
