package com.example.proyectate.feature.tareas;

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
@RequestMapping("tareas")
@RequiredArgsConstructor
public class TareaApiController {
    private final TareaService tareaService;

    @PostMapping
    public ResponseEntity<Tarea> create (@RequestBody Tarea tarea) {
        Tarea nuevoTarea = tareaService.update(tarea);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoTarea);
    }

    @GetMapping()
    public ResponseEntity<List<Tarea>> getAll () {
        return ResponseEntity.ok(tareaService.selectAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarea> getOne(@PathVariable Long id) {
        Tarea tarea = tareaService.selectOne(id);
        if (tarea == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tarea);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarea> update(@PathVariable Long id, @RequestBody Tarea tarea) {
        Tarea existente = tareaService.selectOne(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        tarea.setId(id);
        Tarea actualizada = tareaService.update(tarea);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable long id) {
        Tarea existente = tareaService.selectOne(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        tareaService.delete(id);
        return ResponseEntity.noContent().build();
    }  
    
}
