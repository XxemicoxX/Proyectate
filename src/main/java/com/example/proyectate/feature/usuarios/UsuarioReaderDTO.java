package com.example.proyectate.feature.usuarios;

import com.example.proyectate.util.RolSistema;

public record UsuarioReaderDTO (
     Long id,
     String nombre,
     String email,
     String contrasena,
     RolSistema rol
){
    
}
