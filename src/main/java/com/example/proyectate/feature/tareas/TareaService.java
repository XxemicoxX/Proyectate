package com.example.proyectate.feature.tareas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TareaService {
    @Autowired
    private final TareaRepository repository;

    public List<Tarea> selectAll() {
        return repository.findAll();
    }

    public Tarea selectOne (Long id) {
        return repository.findById(id).orElse(null);
    }

    public Tarea update (Tarea tarea) {
        return repository.save(tarea);
    }

    public void delete (Long id) {
        repository.deleteById(id);
    }

}
