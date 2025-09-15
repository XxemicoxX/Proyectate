package com.example.proyectate.feature.etiquetas;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("etiquetas")
@RequiredArgsConstructor
public class EtiquetaApiController {
    private final EtiquetaService etiquetaService;

    @PostMapping
    public ResponseEntity<Etiqueta> create (@RequestBody Etiqueta etiqueta){
        Etiqueta nuevaEtiqueta = etiquetaService.update(etiqueta);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaEtiqueta);
    }

    @GetMapping()
    public ResponseEntity<List<Etiqueta>> getAll() {
        return ResponseEntity.ok(etiquetaService.selectAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Etiqueta> getOne (@PathVariable Long id) {
        Etiqueta etiqueta = etiquetaService.selectOne(id);
        if (etiqueta == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(etiqueta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Etiqueta> update(@PathVariable Long id, @RequestBody Etiqueta etiqueta) {
        Etiqueta existente = etiquetaService.selectOne(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        etiqueta.setId(id);
        Etiqueta actualizada = etiquetaService.update(etiqueta);
        return ResponseEntity.ok(actualizada);
    }   

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Etiqueta existente = etiquetaService.selectOne(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        etiquetaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
