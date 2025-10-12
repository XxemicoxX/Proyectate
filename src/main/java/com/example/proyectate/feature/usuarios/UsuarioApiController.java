package com.example.proyectate.feature.usuarios;

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
@RequestMapping("api/usuarios")
@RequiredArgsConstructor
public class UsuarioApiController {
    private final UsuarioService usuarioService;

    @GetMapping()
    public ResponseEntity<List<UsuarioReaderDTO>> getAll() {
       List<UsuarioReaderDTO> usuarios = usuarioService.getAllUsuarios();
       if (usuarios.isEmpty()) {
        throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No hay usuarios registrados");
       }
       return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioReaderDTO> getOne(@PathVariable Long id) {
       try {
         return ResponseEntity.ok(usuarioService.getUsuarioById(id));
       } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
       }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsuarioReaderDTO> insertUsuario(@Valid @RequestBody UsuarioWriterDTO usuario){
       try {
            return ResponseEntity.ok(usuarioService.addUsuario(usuario));
       } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
       }
    }


    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsuarioReaderDTO> updateUsuario(@Valid @RequestBody UsuarioWriterDTO usuario){
       try {
            return ResponseEntity.ok(usuarioService.updUsuario(usuario));
       } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
       }
    }   

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUsuario(@PathVariable Long id) {
        try {
             usuarioService.deleteUsuario(id);
             return ResponseEntity.ok("Usuario eliminado");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
