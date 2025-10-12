package com.example.proyectate.feature.etiquetas;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EtiquetaWriterDTO (
    Long id,
    @NotBlank(message = "Debes ingresar el nombre")
    @Size(max = 15)
    String nombre
) {
    
}
