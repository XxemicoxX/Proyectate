package com.example.proyectate.feature.proyectos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProyectoService {
    @Autowired
    private final ProyectoRepository repository;

    public List<Proyecto> selectAll() {
        return repository.findAll();
    }

    public Proyecto selectOne (Long id) {
        return repository.findById(id).orElse(null);
    }

    public Proyecto update (Proyecto proyecto) {
        return repository.save(proyecto);
    }

    public void delete (Long id) {
        repository.deleteById(id);
    }

}
