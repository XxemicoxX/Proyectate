package com.example.proyectate.feature.proyectos;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("proyectos")
@RequiredArgsConstructor
public class ProyectoApiController {
    private final ProyectoService proyectoService;

    @PostMapping
    public ResponseEntity<Proyecto> create (@RequestBody Proyecto proyecto) {
        Proyecto nuevoProyecto = proyectoService.update(proyecto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProyecto);
    }

    @GetMapping()
    public ResponseEntity<List<Proyecto>> getAll () {
        return ResponseEntity.ok(proyectoService.selectAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proyecto> getOne(@PathVariable Long id) {
        Proyecto proyecto = proyectoService.selectOne(id);
        if (proyecto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(proyecto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Proyecto> update(@PathVariable Long id, @RequestBody Proyecto proyecto) {
        Proyecto existente = proyectoService.selectOne(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        proyecto.setId(id);
        Proyecto actualizada = proyectoService.update(proyecto);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable long id) {
        Proyecto existente = proyectoService.selectOne(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        proyectoService.delete(id);
        return ResponseEntity.noContent().build();
    }  
    
}
