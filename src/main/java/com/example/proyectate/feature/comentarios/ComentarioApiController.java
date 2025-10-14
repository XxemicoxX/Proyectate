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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/comentarios")
@RequiredArgsConstructor
public class ComentarioApiController {

    private final ComentarioService comentarioService;

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

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ComentarioReaderDTO> insertComentario(@Valid @RequestBody ComentarioWriterDTO comentario){
       try {
            return ResponseEntity.ok(comentarioService.addComentario(comentario));
       } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
       }
    }


    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ComentarioReaderDTO> updateComentario(@Valid @RequestBody ComentarioWriterDTO comentario){
       try {
            return ResponseEntity.ok(comentarioService.updComentario(comentario));
       } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
       }
    }   

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComentario(@PathVariable Long id) {
        try {
             comentarioService.deleteComentario(id);
             return ResponseEntity.ok("Comentario eliminado");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

}
