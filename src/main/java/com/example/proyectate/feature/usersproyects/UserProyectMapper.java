package com.example.proyectate.feature.usersproyects;

import com.example.proyectate.feature.users.User;
import com.example.proyectate.feature.proyectos.Proyecto;
import com.example.proyectate.util.Mapper;
import org.springframework.stereotype.Component;

@Component
public class UserProyectMapper implements Mapper<UserProyect, UserProyectWriterDTO, UserProyectReaderDTO> {
    @Override
    public UserProyect toEntity(UserProyectWriterDTO dto) {
        return UserProyect.builder()
        .id(dto.id())
        .user(User.builder().id(dto.user()).build())
        .proyect(Proyecto.builder().id(dto.proyect()).build())
        .rol(dto.rol())
        .build();
    }

    @Override
    public UserProyectReaderDTO toDto(UserProyect entity) {
        return new UserProyectReaderDTO(
        entity.getId(),
        entity.getUser() != null ? entity.getUser().getId() : null,
        entity.getProyect() != null ? entity.getProyect().getId() : null,
        entity.getRol());
    }

}
