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
        return Tarea.builder()
        .id(dto.id())
        .titulo(dto.titulo())
        .descripcion(dto.descripcion())
        .prioridad(dto.prioridad())
        .estado(dto.estado())
        .etiqueta(Etiqueta.builder().id(dto.idEtiqueta()).build())
        .proyecto(Proyecto.builder().id(dto.idProyecto()).build())
        .user(User.builder().id(dto.idUsuario()).build())
        .build();
    }

    @Override
    public TareaReaderDTO toDto(Tarea entity) {
        return new TareaReaderDTO(
            entity.getId(),
            entity.getTitulo(),
            entity.getDescripcion(),
            entity.getEstado(),
            entity.getPrioridad(),
            entity.getProyecto().getId(),
            entity.getEtiqueta().getId(),
            entity.getUser().getId());
    }

}
