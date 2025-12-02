package com.example.proyectate.feature.proyectos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, Long>{
    // Buscar proyectos donde el usuario es creador O está en usuarios_proyectos
    @Query("""
        SELECT DISTINCT p FROM Proyecto p 
        LEFT JOIN UserProyect up ON up.proyect.id = p.id 
        WHERE p.idUsuario.id = :userId 
        OR up.user.id = :userId
        """)
    List<Proyecto> findProyectosByUserId(@Param("userId") Long userId);
}
