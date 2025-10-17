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

import com.example.proyectate.feature.usuarios.UsuarioMapper;
import com.example.proyectate.feature.usuarios.UsuarioReaderDTO;
import com.example.proyectate.feature.usuarios.UsuarioRepository;
import com.example.proyectate.feature.usuarios.UsuarioService;
import com.example.proyectate.feature.usuarios.UsuarioWriterDTO;
import com.example.proyectate.feature.usuarios.Usuarios;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private UsuarioMapper usuarioMapper;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    public void getAllUsuarios() {
        Usuarios entidad = mock(Usuarios.class);
        UsuarioReaderDTO dto = mock(UsuarioReaderDTO.class);

        when(usuarioRepository.findAll()).thenReturn(List.of(entidad));
        when(usuarioMapper.toDto(entidad)).thenReturn(dto);

        List<UsuarioReaderDTO> resultado = usuarioService.getAllUsuarios();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertSame(dto, resultado.get(0));

        verify(usuarioRepository).findAll();
        verify(usuarioMapper).toDto(entidad);
    }

    @Test
    public void getUsuarioById() {
        Usuarios entidad = mock(Usuarios.class);
        UsuarioReaderDTO dto = mock(UsuarioReaderDTO.class);

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(entidad));
        when(usuarioMapper.toDto(entidad)).thenReturn(dto);

        UsuarioReaderDTO resultado = usuarioService.getUsuarioById(1L);

        assertSame(dto, resultado);
        verify(usuarioRepository).findById(1L);
        verify(usuarioMapper).toDto(entidad);
    }

    @Test
    public void addUsuario() {
        UsuarioWriterDTO writer = mock(UsuarioWriterDTO.class);
        Usuarios entidad = mock(Usuarios.class);
        UsuarioReaderDTO dto = mock(UsuarioReaderDTO.class);

        when(usuarioMapper.toEntity(writer)).thenReturn(entidad);
        when(usuarioRepository.save(entidad)).thenReturn(entidad);
        when(usuarioMapper.toDto(entidad)).thenReturn(dto);

        UsuarioReaderDTO resultado = usuarioService.addUsuario(writer);

        assertSame(dto, resultado);
        verify(usuarioMapper).toEntity(writer);
        verify(usuarioRepository).save(entidad);
        verify(usuarioMapper).toDto(entidad);
    }

    @Test
    public void updUsuario() throws Exception {
        UsuarioWriterDTO writer = mock(UsuarioWriterDTO.class);
        Usuarios entidad = mock(Usuarios.class);
        UsuarioReaderDTO dto = mock(UsuarioReaderDTO.class);

        when(writer.id()).thenReturn(1L);
        when(usuarioRepository.existsById(1L)).thenReturn(true);
        when(usuarioMapper.toEntity(writer)).thenReturn(entidad);
        when(usuarioRepository.save(entidad)).thenReturn(entidad);
        when(usuarioMapper.toDto(entidad)).thenReturn(dto);

        UsuarioReaderDTO resultado = usuarioService.updUsuario(writer);

        assertSame(dto, resultado);
        verify(usuarioRepository).existsById(1L);
        verify(usuarioRepository).save(entidad);
    }

    @Test
    public void deleteUsuario() throws Exception{
        when(usuarioRepository.existsById(2L)).thenReturn(true);
        String msg = usuarioService.deleteUsuario(2L);

        assertTrue(msg.contains("Usuario eliminada con el ID: 2"));
        verify(usuarioRepository).existsById(2L);
        verify(usuarioRepository).deleteById(2L);
        
    }
    
}
