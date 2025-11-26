package com.example.proyectate.util;

public interface Mapper<E, DW, DR> {
    E toEntity(DW dto);
    DR toDto(E entity);
}
