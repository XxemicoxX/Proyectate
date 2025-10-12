package com.example.proyectate.feature.usuarios;

import org.springframework.stereotype.Component;

import com.example.proyectate.util.Mapper;

@Component
public class UsuarioMapper implements Mapper<Usuario, UsuarioWriterDTO, UsuarioReaderDTO> {

    @Override
    public Usuario toEntity(UsuarioWriterDTO dto) {
        return Usuario.builder()
        .id(dto.id())
        .nombre(dto.nombre())
        .email(dto.email())
        .contrasena(dto.contrasena())
        .rol(dto.rol())
        .build();
    }

    @Override
    public UsuarioReaderDTO toDto(Usuario entity) {
       return new UsuarioReaderDTO(
        entity.getId(),
        entity.getNombre(),
        entity.getEmail(),
        entity.getContrasena(),
        entity.getRol()
       );
    }

}
