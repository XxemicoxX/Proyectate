package com.example.proyectate.feature.comentarios;

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
@RequestMapping("comentarios")
@RequiredArgsConstructor
public class ComentarioApiController {
    private final ComentarioService comentarioService;

    @PostMapping
    public ResponseEntity<Comentario> create (@RequestBody Comentario comentario) {
        Comentario nuevoComentario = comentarioService.update(comentario);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoComentario);
    }

    @GetMapping()
    public ResponseEntity<List<Comentario>> getAll () {
        return ResponseEntity.ok(comentarioService.selectAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comentario> getOne(@PathVariable Long id) {
        Comentario comentario = comentarioService.selectOne(id);
        if (comentario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(comentario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comentario> update(@PathVariable Long id, @RequestBody Comentario comentario) {
        Comentario existente = comentarioService.selectOne(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        comentario.setId(id);
        Comentario actualizada = comentarioService.update(comentario);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable long id) {
        Comentario existente = comentarioService.selectOne(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        comentarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    
}
