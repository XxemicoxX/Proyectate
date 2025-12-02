package com.example.proyectate.feature.tareas;

import org.springframework.stereotype.Component;

import com.example.proyectate.feature.etiquetas.Etiqueta;
import com.example.proyectate.feature.proyectos.Proyecto;
import com.example.proyectate.feature.users.User;
import com.example.proyectate.util.Mapper;

@Component
public class TareaMapper implements Mapper<Tarea, TareaWriterDTO, TareaReaderDTO>{

    @Override
    public Tarea toEntity(TareaWriterDTO dto) {
        Tarea.TareaBuilder builder = Tarea.builder()
            .id(dto.id())
            .titulo(dto.titulo())
            .descripcion(dto.descripcion())
            .prioridad(dto.prioridad())
            .estado(dto.estado())
            .proyecto(Proyecto.builder().id(dto.idProyecto()).build());
        
        // Solo agregar etiqueta si existe
        if (dto.idEtiqueta() != null) {
            builder.etiqueta(Etiqueta.builder().id(dto.idEtiqueta()).build());
        }
        
        // Solo agregar usuario si existe
        if (dto.idUsuario() != null) {
            builder.user(User.builder().id(dto.idUsuario()).build());
        }
        
        return builder.build();
    }

    @Override
    public TareaReaderDTO toDto(Tarea entity) {
        return new TareaReaderDTO(
            entity.getId(),
            entity.getTitulo(),
            entity.getDescripcion(),
            entity.getPrioridad(),
            entity.getEstado(),
            entity.getProyecto() != null ? entity.getProyecto().getId() : null,
            entity.getEtiqueta() != null ? entity.getEtiqueta().getId() : null,
            entity.getUser() != null ? entity.getUser().getId() : null
        );
    }
}
