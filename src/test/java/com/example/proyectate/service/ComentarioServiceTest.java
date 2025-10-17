package com.example.proyectate.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.proyectate.feature.comentarios.Comentario;
import com.example.proyectate.feature.comentarios.ComentarioMapper;
import com.example.proyectate.feature.comentarios.ComentarioReaderDTO;
import com.example.proyectate.feature.comentarios.ComentarioRepository;
import com.example.proyectate.feature.comentarios.ComentarioService;
import com.example.proyectate.feature.comentarios.ComentarioWriterDTO;


@ExtendWith(MockitoExtension.class)
public class ComentarioServiceTest {

    @Mock
    private ComentarioRepository comentarioRepository;

    @Mock
    private ComentarioMapper comentarioMapper;



    @InjectMocks
    private ComentarioService comentarioService;


    @Test
    public void getAllComentario() {
        Comentario entidad = mock(Comentario.class);
        ComentarioReaderDTO dto = mock(ComentarioReaderDTO.class);

        when(comentarioRepository.findAll()).thenReturn(List.of(entidad));
        when(comentarioMapper.toDto(entidad)).thenReturn(dto);

        List<ComentarioReaderDTO> resultado = comentarioService.getAllComentarios();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertSame(dto, resultado.get(0));

        verify(comentarioRepository).findAll();
        verify(comentarioMapper).toDto(entidad);
    }

    @Test
    public void getComentarioById() {
        Comentario entidad = mock(Comentario.class);
        ComentarioReaderDTO dto = mock(ComentarioReaderDTO.class);

        when(comentarioRepository.findById(1L)).thenReturn(Optional.of(entidad));
        when(comentarioMapper.toDto(entidad)).thenReturn(dto);

        ComentarioReaderDTO resultado = comentarioService.getComentarioById(1L);

        assertSame(dto, resultado);
        verify(comentarioRepository).findById(1L);
        verify(comentarioMapper).toDto(entidad);
    }

    @Test
    public void addComentario() {
        ComentarioWriterDTO writer = mock(ComentarioWriterDTO.class);
        Comentario entidad = mock(Comentario.class);
        ComentarioReaderDTO dto = mock(ComentarioReaderDTO.class);

        when(comentarioMapper.toEntity(writer)).thenReturn(entidad);
        when(comentarioRepository.save(entidad)).thenReturn(entidad);
        when(comentarioMapper.toDto(entidad)).thenReturn(dto);

        ComentarioReaderDTO resultado = comentarioService.addComentario(writer);

        assertSame(dto, resultado);
        verify(comentarioMapper).toEntity(writer);
        verify(comentarioRepository).save(entidad);
        verify(comentarioMapper).toDto(entidad);
    }

    @Test
    public void updComentario() throws Exception {
        ComentarioWriterDTO writer = mock(ComentarioWriterDTO.class);
        Comentario entidad = mock(Comentario.class);
        ComentarioReaderDTO dto = mock(ComentarioReaderDTO.class);

        when(writer.id()).thenReturn(1L);
        when(comentarioRepository.existsById(1L)).thenReturn(true);
        when(comentarioMapper.toEntity(writer)).thenReturn(entidad);
        when(comentarioRepository.save(entidad)).thenReturn(entidad);
        when(comentarioMapper.toDto(entidad)).thenReturn(dto);

        ComentarioReaderDTO resultado = comentarioService.updComentario(writer);

        assertSame(dto, resultado);
        verify(comentarioRepository).existsById(1L);
        verify(comentarioRepository).save(entidad);
    }
    
    @Test
    public void deleteComentario() throws Exception{
        when(comentarioRepository.existsById(1L)).thenReturn(true);
        String msg = comentarioService.deleteComentario(1L);

        assertTrue(msg.contains("Comentario eliminada con el ID: 1"));
        verify(comentarioRepository).existsById(1L);
        verify(comentarioRepository).deleteById(1L);
        
    }
    
}
