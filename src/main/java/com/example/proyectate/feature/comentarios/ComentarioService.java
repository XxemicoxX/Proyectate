package com.example.proyectate.feature.comentarios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ComentarioService {
    @Autowired
    private final ComentarioRepository repository;

    public List<Comentario> selectAll() {
        return repository.findAll();
    }

    public Comentario selectOne (Long id) {
        return repository.findById(id).orElse(null);
    }

    public Comentario update (Comentario comentario) {
        return repository.save(comentario);
    }

    public void delete (Long id) {
        repository.deleteById(id);
    }

}
