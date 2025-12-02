package com.example.proyectate.feature.comentarios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long>{
    List<Comentario> findByTareaIdOrderByFechaCreacionDesc(Long tareaId);
    List<Comentario> findByUsuarioId(Long usuarioId);
    
}
