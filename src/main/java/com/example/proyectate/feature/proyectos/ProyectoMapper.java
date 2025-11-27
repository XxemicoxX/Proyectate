package com.example.proyectate.feature.proyectos;

import org.springframework.stereotype.Component;

import com.example.proyectate.feature.users.User;
import com.example.proyectate.util.Mapper;

@Component
public class ProyectoMapper implements Mapper<Proyecto, ProyectoWriterDTO, ProyectoReaderDTO>{

    @Override
    public Proyecto toEntity(ProyectoWriterDTO dto) {
        return Proyecto.builder()
        .id(dto.id())
        .nombre(dto.nombre())
        .descripcion(dto.descripcion())
        .fechaInicio(dto.fechaInicio())
        .estado(dto.estado())
        .idUsuario(User.builder().id(dto.id()).build())
        .build();
    }

    @Override
    public ProyectoReaderDTO toDto(Proyecto entity) {
       return new ProyectoReaderDTO(
       entity.getId(),
       entity.getNombre(),
       entity.getDescripcion(),
       entity.getFechaInicio(),
       entity.getEstado(),
       entity.getIdUsuario().getId());
    }

}
