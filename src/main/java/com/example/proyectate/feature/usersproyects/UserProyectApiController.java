package com.example.proyectate.feature.usersproyects;

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
@RequestMapping("usuarios-proyectos")
@RequiredArgsConstructor
public class UserProyectApiController {

    private final UserProyectService userProyectService;

    @GetMapping()
    public ResponseEntity<List<UserProyectReaderDTO>> getAll() {
        List<UserProyectReaderDTO> usuariosProyectos = userProyectService.getAllUserProyects();
        if (usuariosProyectos.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No hay relaciones usuario-proyecto registradas");
        }
        return ResponseEntity.ok(usuariosProyectos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserProyectReaderDTO> getOne(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(userProyectService.getUserProyectById(id));
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/usuario/{userId}")
    public ResponseEntity<List<UserProyectReaderDTO>> getByUserId(@PathVariable Long userId) {
        try {
            List<UserProyectReaderDTO> result = userProyectService.getUserProyectsByUserId(userId);
            if (result.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No hay proyectos para este usuario");
            }
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/proyecto/{proyectoId}")
    public ResponseEntity<List<UserProyectReaderDTO>> getByProyectoId(@PathVariable Long proyectoId) {
        try {
            List<UserProyectReaderDTO> result = userProyectService.getUserProyectsByProyectoId(proyectoId);
            if (result.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No hay usuarios en este proyecto");
            }
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/crear")
    public ResponseEntity<UserProyectReaderDTO> insertUserProyect(
            @Valid @RequestBody UserProyectWriterDTO userProyect) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(userProyectService.addUserProyect(userProyect));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/{id}")
    public ResponseEntity<UserProyectReaderDTO> updateUserProyect(
            @PathVariable Long id,
            @Valid @RequestBody UserProyectWriterDTO userProyect) {
        try {
            // Crear nuevo DTO con el ID de la ruta
            UserProyectWriterDTO updatedDto = new UserProyectWriterDTO(
                    id, // id desde la ruta
                    userProyect.user(), // id_usuario del body
                    userProyect.proyect(), // id_proyecto del body
                    userProyect.rol() // rol del body
            );
            return ResponseEntity.ok(userProyectService.updateUserProyect(updatedDto));
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserProyect(@PathVariable Long id) {
        try {
            userProyectService.deleteUserProyect(id);
            return ResponseEntity.ok("Relación usuario-proyecto eliminada");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/proyecto/{proyectoId}/detalles")
    public ResponseEntity<List<UserProyectDetailDTO>> getUserProyectDetails(@PathVariable Long proyectoId) {
        try {
            List<UserProyectDetailDTO> result = userProyectService.getUserProyectDetailsById(proyectoId);
            if (result.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No hay usuarios en este proyecto");
            }
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
