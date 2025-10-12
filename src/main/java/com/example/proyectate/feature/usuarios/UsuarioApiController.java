package com.example.proyectate.feature.usuarios;

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
@RequestMapping("usuarios")
@RequiredArgsConstructor
public class UsuarioApiController {
    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuarios> create (@RequestBody Usuarios usuario) {
        Usuarios nuevoUsuario = usuarioService.update(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }

    @GetMapping()
    public ResponseEntity<List<Usuarios>> getAll () {
        return ResponseEntity.ok(usuarioService.selectAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuarios> getOne(@PathVariable Long id) {
        Usuarios usuario = usuarioService.selectOne(id);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuarios> update(@PathVariable Long id, @RequestBody Usuarios usuario) {
        Usuarios existente = usuarioService.selectOne(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        usuario.setId(id);
        Usuarios actualizada = usuarioService.update(usuario);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable long id) {
        Usuarios existente = usuarioService.selectOne(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }  
    
}
