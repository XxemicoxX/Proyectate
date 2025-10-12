package com.example.proyectate.feature.comentarios;

import org.springframework.stereotype.Component;

import com.example.proyectate.feature.tareas.Tarea;
import com.example.proyectate.feature.usuarios.Usuario;
import com.example.proyectate.util.Mapper;

@Component
public class ComentarioMapper implements Mapper<Comentario, ComentarioWriterDTO, ComentarioReaderDTO>{

    @Override
    public Comentario toEntity(ComentarioWriterDTO dto) {
        return Comentario.builder()
            .id(dto.id())
            .contenido(dto.contenido())
            .fecha_creacion(dto.fechaCreacion())
            .usuario(Usuario.builder().id(dto.usuarioId()).build())
            .tarea(Tarea.builder().id(dto.tareaId()).build())
            .build();
    }

    @Override
    public ComentarioReaderDTO toDto(Comentario entity) {
        return new ComentarioReaderDTO(
            entity.getId(),
            entity.getContenido(),
            entity.getFecha_creacion(),
            entity.getUsuario().getId(),
            entity.getTarea().getId());
    }
    
}
