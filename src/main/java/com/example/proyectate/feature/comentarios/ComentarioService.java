package com.example.proyectate.feature.comentarios;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.proyectate.feature.tareas.TareaRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ComentarioService {
    
    private final ComentarioRepository comentarioRepository;
    private final ComentarioMapper mapper;
    private final TareaRepository tareaRepository;
    
    public List<ComentarioReaderDTO> getAllComentarios() {
        return comentarioRepository.findAll()
            .stream()
            .map(mapper::toDto)
            .toList();
    }
    
    public ComentarioReaderDTO getComentarioById(Long id) {
        Comentario comentario = comentarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Comentario no encontrado"));
        return mapper.toDto(comentario);
    }
    
    public List<ComentarioReaderDTO> getComentariosByTarea(Long tareaId) {
        return comentarioRepository.findByTareaIdOrderByFechaCreacionDesc(tareaId)
            .stream()
            .map(mapper::toDto)
            .toList();
    }
    
    public ComentarioReaderDTO addComentario(ComentarioWriterDTO dto, Long usuarioId) {
        // Usar el método personalizado con usuario
        Comentario comentario = mapper.toEntityWithUser(dto, usuarioId);
        Comentario saved = comentarioRepository.save(comentario);
        return mapper.toDto(saved);
    }
    
    public ComentarioReaderDTO updComentario(ComentarioWriterDTO dto, Long usuarioId) {
        Comentario comentarioExistente = comentarioRepository.findById(dto.id())
            .orElseThrow(() -> new RuntimeException("Comentario no encontrado"));
        
        // Verificar que el usuario sea el dueño del comentario
        if (!comentarioExistente.getUsuario().getId().equals(usuarioId)) {
            throw new RuntimeException("No tienes permiso para editar este comentario");
        }
        
        comentarioExistente.setContenido(dto.contenido());
        
        Comentario saved = comentarioRepository.save(comentarioExistente);
        return mapper.toDto(saved);
    }
    
    public void deleteComentario(Long id, Long usuarioId) {
        Comentario comentario = comentarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Comentario no encontrado"));
        
        // Verificar que el usuario sea el dueño
        if (!comentario.getUsuario().getId().equals(usuarioId)) {
            throw new RuntimeException("No tienes permiso para eliminar este comentario");
        }
        
        comentarioRepository.delete(comentario);
    }
}
