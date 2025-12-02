package com.example.proyectate.feature.invitaciones;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.proyectate.security.JwtService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("invitaciones")
@RequiredArgsConstructor
public class InvitacionApiController {
    
    private final InvitacionService invitacionService;
    private final JwtService jwtService;
    
    @PostMapping("/enviar")
    public ResponseEntity<InvitacionReaderDTO> enviarInvitacion(
            @Valid @RequestBody InvitacionWriterDTO invitacion,
            @RequestHeader("Authorization") String token) {
        try {
            Long idUsuario = extraerIdUsuarioDelToken(token);
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(invitacionService.enviarInvitacion(invitacion, idUsuario));
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
    
    @GetMapping("/pendientes")
    public ResponseEntity<List<InvitacionReaderDTO>> getInvitacionesPendientes(
            @RequestHeader("Authorization") String token) {
        try {
            Long idUsuario = extraerIdUsuarioDelToken(token);
            List<InvitacionReaderDTO> invitaciones = 
                invitacionService.getInvitacionesPendientes(idUsuario);
            return ResponseEntity.ok(invitaciones);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
    
    @GetMapping("/pendientes/contador")
    public ResponseEntity<Long> contarInvitacionesPendientes(
            @RequestHeader("Authorization") String token) {
        try {
            Long idUsuario = extraerIdUsuarioDelToken(token);
            return ResponseEntity.ok(invitacionService.contarInvitacionesPendientes(idUsuario));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
    
    @GetMapping("/enviadas")
    public ResponseEntity<List<InvitacionReaderDTO>> getInvitacionesEnviadas(
            @RequestHeader("Authorization") String token) {
        try {
            Long idUsuario = extraerIdUsuarioDelToken(token);
            return ResponseEntity.ok(invitacionService.getInvitacionesEnviadas(idUsuario));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
    
    @GetMapping("/proyecto/{proyectoId}")
    public ResponseEntity<List<InvitacionReaderDTO>> getInvitacionesPorProyecto(
            @PathVariable Long proyectoId) {
        try {
            return ResponseEntity.ok(invitacionService.getInvitacionesPorProyecto(proyectoId));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
    
    @PutMapping("/{id}/responder")
    public ResponseEntity<InvitacionReaderDTO> responderInvitacion(
            @PathVariable Long id,
            @RequestParam boolean aceptar,
            @RequestHeader("Authorization") String token) {
        try {
            Long idUsuario = extraerIdUsuarioDelToken(token);
            return ResponseEntity.ok(
                invitacionService.responderInvitacion(id, aceptar, idUsuario));
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> cancelarInvitacion(
            @PathVariable Long id,
            @RequestHeader("Authorization") String token) {
        try {
            Long idUsuario = extraerIdUsuarioDelToken(token);
            invitacionService.cancelarInvitacion(id, idUsuario);
            return ResponseEntity.ok("Invitación cancelada exitosamente");
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
    
    private Long extraerIdUsuarioDelToken(String token) {
        String jwt = token.replace("Bearer ", "");
        return jwtService.extractUserId(jwt);
    }
}
