package com.example.proyectate.feature.usuarios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    @Autowired
    private final UsuarioRepository repository;

    public List<Usuarios> selectAll() {
        return repository.findAll();
    }

    public Usuarios selectOne (Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public Usuarios update (Usuarios usuario) {
        return repository.save(usuario);
    }

    public void delete (Long id) {
        repository.deleteById(id);
    }

    //Buscar Email
    public Usuarios getUserByEmail (String email) {
        return repository.findByEmail(email).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

}
