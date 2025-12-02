package com.example.proyectate.feature.tareas;

import java.util.Collections;
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
@RequestMapping("tareas")
@RequiredArgsConstructor
public class TareaApiController {
     private final TareaService tareaService;

     @GetMapping()
     public ResponseEntity<List<TareaReaderDTO>> getAll() {
          List<TareaReaderDTO> tareas = tareaService.getAllTareas();
          if (tareas.isEmpty()) {
               throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No hay tareas registrados");
          }
          return ResponseEntity.ok(tareas);
     }

     @GetMapping("/{id}")
     public ResponseEntity<TareaReaderDTO> getOne(@PathVariable Long id) {
          try {
               return ResponseEntity.ok(tareaService.getTareaById(id));
          } catch (Exception e) {
               throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
          }
     }

     @GetMapping("/titulo/{titulo}")
     public ResponseEntity<List<TareaReaderDTO>> getTitulo(@PathVariable String titulo) {
          try {
               return ResponseEntity.ok(tareaService.getTareaByTitulo(titulo));
          } catch (Exception e) {
               throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
          }
     }

     @GetMapping("/prioridad/{prioridad}")
     public ResponseEntity<List<TareaReaderDTO>> getPrioridad(@PathVariable String prioridad) {
          try {
               return ResponseEntity.ok(tareaService.getTareaByPrioridad(prioridad));
          } catch (Exception e) {
               throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
          }
     }

     @GetMapping("/proyecto/{proyectoId}")
     public ResponseEntity<List<TareaReaderDTO>> getByProyectoId(@PathVariable Long proyectoId) {
          try {
               List<TareaReaderDTO> tareas = tareaService.getTareasByProyectoId(proyectoId);
               return ResponseEntity.ok(tareas.isEmpty() ? Collections.emptyList() : tareas);
          } catch (Exception e) {
               throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
          }
     }

     @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/crear")
     public ResponseEntity<TareaReaderDTO> insertTarea(@Valid @RequestBody TareaWriterDTO tarea) {
          try {
               return ResponseEntity.ok(tareaService.addTarea(tarea));
          } catch (Exception e) {
               throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
          }
     }

     @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/{id}")
     public ResponseEntity<TareaReaderDTO> updateTarea(
               @PathVariable Long id,
               @Valid @RequestBody TareaWriterDTO tarea) {
          try {
               // Crear nuevo DTO con el ID de la ruta
               TareaWriterDTO updatedDto = new TareaWriterDTO(
                         id,
                         tarea.titulo(),
                         tarea.descripcion(),
                         tarea.prioridad(),
                         tarea.estado(),
                         tarea.idProyecto(),
                         tarea.idEtiqueta(),
                         tarea.idUsuario());
               return ResponseEntity.ok(tareaService.updTarea(updatedDto));
          } catch (Exception e) {
               throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
          }
     }

     @DeleteMapping("/{id}")
     public ResponseEntity<String> deleteTarea(@PathVariable Long id) {
          try {
               tareaService.deleteTarea(id);
               return ResponseEntity.ok("Tarea eliminado");
          } catch (Exception e) {
               throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
          }
     }
}