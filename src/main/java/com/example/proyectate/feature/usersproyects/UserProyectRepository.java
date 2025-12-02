package com.example.proyectate.feature.usersproyects;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserProyectRepository extends JpaRepository<UserProyect, Long> {

    List<UserProyect> findByUser_Id(Long userId);

    List<UserProyect> findByProyect_Id(Long proyectoId);

    boolean existsByUser_IdAndProyect_Id(Long userId, Long proyectId);
    
    // Nuevo método con query nativa
    @Query(value = """
            SELECT 
                up.id_usuarios_proyectos as id,
                up.id_usuario as idUsuario,
                up.id_proyecto as idProyecto,
                up.rol as rol,
                u.name as nombreUsuario,
                u.email as emailUsuario
            FROM usuarios_proyectos up
            INNER JOIN user u ON up.id_usuario = u.id_usuario
            WHERE up.id_proyecto = :proyectoId
            """, nativeQuery = true)
    List<Object[]> findUserProyectDetailsById(Long proyectoId);
}