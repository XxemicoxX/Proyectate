package com.example.proyectate.feature.etiquetas;

import org.springframework.stereotype.Component;

import com.example.proyectate.util.Mapper;

@Component
public class EtiquetaMapper implements Mapper<Etiqueta, EtiquetaWriterDTO, EtiquetaReaderDTO> {

    @Override
    public Etiqueta toEntity(EtiquetaWriterDTO dto) {
        return Etiqueta.builder()
            .id(dto.id())
            .nombre(dto.nombre())
            .build();
    }

    @Override
    public EtiquetaReaderDTO toDto(Etiqueta entity) {
        return new EtiquetaReaderDTO(
            entity.getId(),
            entity.getNombre());
    }
    
}
