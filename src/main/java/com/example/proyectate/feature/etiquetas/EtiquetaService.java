package com.example.proyectate.feature.etiquetas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EtiquetaService {
    @Autowired
    private EtiquetaRepository repository;

    public List<Etiqueta> selectAll() {
        return repository.findAll();
    }

    public Etiqueta selectOne(Long id) {
        return repository.findById(id).orElse(null);
    }
    
    public Etiqueta update (Etiqueta etiqueta) {
        return repository.save(etiqueta);
    }
    
    public void delete (Long id) {
        repository.deleteById(id);
    }
}
