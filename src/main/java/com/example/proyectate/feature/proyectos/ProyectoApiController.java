package com.example.proyectate.feature.proyectos;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("proyectos")
@RequiredArgsConstructor
public class ProyectoApiController {
     private final ProyectoService proyectoService;

    @GetMapping()
    public ResponseEntity<List<ProyectoReaderDTO>> getAll() {
       List<ProyectoReaderDTO> proyectos = proyectoService.getAllProyectos();
       if (proyectos.isEmpty()) {
        throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No hay proyectos registrados");
       }
       return ResponseEntity.ok(proyectos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProyectoReaderDTO> getOne(@PathVariable Long id) {
       try {
         return ResponseEntity.ok(proyectoService.getProyectoById(id));
       } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
       }
    }     

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/crear")
    public ResponseEntity<ProyectoReaderDTO> insertProyecto(@Valid @RequestBody ProyectoWriterDTO proyecto){
       try {
            return ResponseEntity.ok(proyectoService.addProyecto(proyecto));
       } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
       }
    }


    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/{id}")
    public ResponseEntity<ProyectoReaderDTO> updateProyecto(@Valid @RequestBody ProyectoWriterDTO proyecto){
       try {
            return ResponseEntity.ok(proyectoService.updProyecto(proyecto));
       } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
       }
    }   

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProyecto(@PathVariable Long id) {
        try {
             proyectoService.deleteProyecto(id);
             return ResponseEntity.ok("Proyecto eliminado");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
