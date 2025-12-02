package com.example.proyectate.feature.comentarios;

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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.proyectate.auth.JwtService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("comentarios")
@RequiredArgsConstructor
public class ComentarioApiController {

    private final ComentarioService comentarioService;
    private final JwtService jwtService;

    @GetMapping()
    public ResponseEntity<List<ComentarioReaderDTO>> getAll() {
        List<ComentarioReaderDTO> comentarios = comentarioService.getAllComentarios();
        if (comentarios.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No hay comentarios registrados");
        }
        return ResponseEntity.ok(comentarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComentarioReaderDTO> getOne(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(comentarioService.getComentarioById(id));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
    
    @GetMapping("/tarea/{tareaId}")
    public ResponseEntity<List<ComentarioReaderDTO>> getByTarea(@PathVariable Long tareaId) {
        try {
            List<ComentarioReaderDTO> comentarios = comentarioService.getComentariosByTarea(tareaId);
            return ResponseEntity.ok(comentarios);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/crear")
    public ResponseEntity<ComentarioReaderDTO> insertComentario(
            @Valid @RequestBody ComentarioWriterDTO comentario,
            @RequestHeader("Authorization") String token) {
        try {
            Long usuarioId = extraerIdUsuarioDelToken(token);
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(comentarioService.addComentario(comentario, usuarioId));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/{id}")
    public ResponseEntity<ComentarioReaderDTO> updateComentario(
            @PathVariable Long id,
            @Valid @RequestBody ComentarioWriterDTO comentario,
            @RequestHeader("Authorization") String token) {
        try {
            Long usuarioId = extraerIdUsuarioDelToken(token);
            ComentarioWriterDTO dtoConId = new ComentarioWriterDTO(
                id,
                comentario.contenido(),
                comentario.tareaId()
            );
            return ResponseEntity.ok(comentarioService.updComentario(dtoConId, usuarioId));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComentario(
            @PathVariable Long id,
            @RequestHeader("Authorization") String token) {
        try {
            Long usuarioId = extraerIdUsuarioDelToken(token);
            comentarioService.deleteComentario(id, usuarioId);
            return ResponseEntity.ok("Comentario eliminado");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
    
    private Long extraerIdUsuarioDelToken(String token) {
        String jwt = token.replace("Bearer ", "");
        return jwtService.extractUserId(jwt);
    }
}
