package com.example.proyectate.feature.users;

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
@RequestMapping("users")
@RequiredArgsConstructor
public class UserApiController {
     private final UserService userService;

     @GetMapping()
     public ResponseEntity<List<UserReaderDTO>> getAll() {
          List<UserReaderDTO> usuarios = userService.getAllUsers();
          if (usuarios.isEmpty()) {
               throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No hay usuarios registrados");
          }
          return ResponseEntity.ok(usuarios);
     }

     @GetMapping("/{id}")
     public ResponseEntity<UserReaderDTO> getOne(@PathVariable Long id) {
          try {
               return ResponseEntity.ok(userService.getUserById(id));
          } catch (Exception e) {
               throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
          }
     }

     @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
     public ResponseEntity<UserReaderDTO> insertUsuario(@Valid @RequestBody UserWriterDTO usuario) {
          try {
               return ResponseEntity.ok(userService.addUser(usuario));
          } catch (Exception e) {
               throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
          }
     }

     @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
     public ResponseEntity<UserReaderDTO> updateUsuario(@Valid @RequestBody UserWriterDTO usuario) {
          try {
               return ResponseEntity.ok(userService.updUser(usuario));
          } catch (Exception e) {
               throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
          }
     }

     @DeleteMapping("/{id}")
     public ResponseEntity<String> deleteUsuario(@PathVariable Long id) {
          try {
               userService.deleteUser(id);
               return ResponseEntity.ok("Usuario eliminado");
          } catch (Exception e) {
               throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
          }
     }

     @GetMapping("/test-error")
     public void testError() {
          throw new RuntimeException("Error 500 de prueba");
     }

     
}
