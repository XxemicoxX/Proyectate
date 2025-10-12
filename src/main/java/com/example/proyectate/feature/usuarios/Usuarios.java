package com.example.proyectate.feature.usuarios;

import com.example.proyectate.util.Role;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;
    @Column(nullable = false, length = 50)
    private String nombre;
    @Column(nullable = false, length = 50, unique = true)
    private String email;
    @Column(nullable = false)
    private String contrasena;
    @Enumerated(EnumType.STRING)
    private Role rol;
}
