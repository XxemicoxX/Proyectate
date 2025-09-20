package com.example.proyectate.feature.etiquetas;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EtiquetaWriterDTO (
    Long id,
    @NotBlank
    @Size(max = 15)
    String nombre
) {
    
}
