package com.example.proyectate.feature.users;

import org.springframework.stereotype.Component;

import com.example.proyectate.util.Mapper;

@Component
public class UserMapper implements Mapper<User, UserWriterDTO, UserReaderDTO> {

    @Override
    public User toEntity(UserWriterDTO dto) {
        return User.builder()
        .id(dto.id())
        .name(dto.name())
        .email(dto.email())
        .password(dto.password())
        .rol(dto.rol())
        .build();
    }

    @Override
    public UserReaderDTO toDto(User entity) {
       return new UserReaderDTO(
        entity.getId(),
        entity.getName(),
        entity.getEmail(),
        entity.getPassword(),
        entity.getRol()
       );
    }

}
