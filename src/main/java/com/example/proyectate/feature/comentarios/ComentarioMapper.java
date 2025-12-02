package com.example.proyectate.feature.comentarios;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.example.proyectate.feature.tareas.Tarea;
import com.example.proyectate.feature.tareas.TareaReaderDTO;
import com.example.proyectate.feature.tareas.TareaWriterDTO;
import com.example.proyectate.feature.users.User;
import com.example.proyectate.util.Mapper;

@Component
public class ComentarioMapper implements Mapper<Comentario, ComentarioWriterDTO, ComentarioReaderDTO>{
    
    @Override
    public Comentario toEntity(ComentarioWriterDTO dto) {
        return Comentario.builder()
            .id(dto.id())
            .contenido(dto.contenido())
            .fechaCreacion(LocalDateTime.now())
            .tarea(Tarea.builder().id(dto.tareaId()).build())
            .build();
    }

    @Override
    public ComentarioReaderDTO toDto(Comentario entity) {
        return new ComentarioReaderDTO(
            entity.getId(),
            entity.getContenido(),
            entity.getFechaCreacion(),
            entity.getUsuario().getId(),
            entity.getUsuario().getName(),
            entity.getTarea().getId()
        );
    }
    
    // Método personalizado para crear entidad con usuario
    public Comentario toEntityWithUser(ComentarioWriterDTO dto, Long usuarioId) {
        return Comentario.builder()
            .id(dto.id())
            .contenido(dto.contenido())
            .fechaCreacion(LocalDateTime.now())
            .usuario(User.builder().id(usuarioId).build())
            .tarea(Tarea.builder().id(dto.tareaId()).build())
            .build();
    }
}
