package com.example.proyectate.feature.etiquetas;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @GetMapping()
    public ResponseEntity<List<EtiquetaReaderDTO>> getAll() {
       List<EtiquetaReaderDTO> etiquetas = etiquetaService.getAllEtiquetas();
       if (etiquetas.isEmpty()) {
        throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No hay etiquetas registrados");
       }
       return ResponseEntity.ok(etiquetas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EtiquetaReaderDTO> getOne(@PathVariable Long id) {
       try {
         return ResponseEntity.ok(etiquetaService.getEtiquetaById(id));
       } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
       }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/crear")
    public ResponseEntity<EtiquetaReaderDTO> insertEtiqueta(@Valid @RequestBody EtiquetaWriterDTO etiqueta){
       try {
            return ResponseEntity.ok(etiquetaService.addEtiqueta(etiqueta));
       } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
       }
    }


    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EtiquetaReaderDTO> updateEtiqueta(@Valid @RequestBody EtiquetaWriterDTO etiqueta){
       try {
            return ResponseEntity.ok(etiquetaService.updEtiqueta(etiqueta));
       } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
       }
    }   

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEtiqueta(@PathVariable Long id) {
        try {
             etiquetaService.deleteEtiqueta(id);
             return ResponseEntity.ok("Etiqueta eliminada");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

}
