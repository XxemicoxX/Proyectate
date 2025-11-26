package com.example.proyectate.feature.users;

import com.example.proyectate.util.RolSistema;

public record UserReaderDTO (
     Long id,
     String nombre,
     String email,
     String contrasena,
     RolSistema rol
){
    
}
