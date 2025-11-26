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

import com.example.proyectate.feature.users.User;
import com.example.proyectate.feature.users.UserMapper;
import com.example.proyectate.feature.users.UserReaderDTO;
import com.example.proyectate.feature.users.UserRepository;
import com.example.proyectate.feature.users.UserService;
import com.example.proyectate.feature.users.UserWriterDTO;


@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UserRepository usuarioRepository;

    @Mock
    private UserMapper usuarioMapper;

    @InjectMocks
    private UserService usuarioService;

    @Test
    public void getAllUser() {
        User entidad = mock(User.class);
        UserReaderDTO dto = mock(UserReaderDTO.class);

        when(usuarioRepository.findAll()).thenReturn(List.of(entidad));
        when(usuarioMapper.toDto(entidad)).thenReturn(dto);

        List<UserReaderDTO> resultado = usuarioService.getAllUsers();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertSame(dto, resultado.get(0));

        verify(usuarioRepository).findAll();
        verify(usuarioMapper).toDto(entidad);
    }

    @Test
    public void getUsuarioById() {
        User entidad = mock(User.class);
        UserReaderDTO dto = mock(UserReaderDTO.class);

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(entidad));
        when(usuarioMapper.toDto(entidad)).thenReturn(dto);

        UserReaderDTO resultado = usuarioService.getUserById(1L);

        assertSame(dto, resultado);
        verify(usuarioRepository).findById(1L);
        verify(usuarioMapper).toDto(entidad);
    }

    @Test
    public void addUsuario() {
        UserWriterDTO writer = mock(UserWriterDTO.class);
        User entidad = mock(User.class);
        UserReaderDTO dto = mock(UserReaderDTO.class);

        when(usuarioMapper.toEntity(writer)).thenReturn(entidad);
        when(usuarioRepository.save(entidad)).thenReturn(entidad);
        when(usuarioMapper.toDto(entidad)).thenReturn(dto);

        UserReaderDTO resultado = usuarioService.addUser(writer);

        assertSame(dto, resultado);
        verify(usuarioMapper).toEntity(writer);
        verify(usuarioRepository).save(entidad);
        verify(usuarioMapper).toDto(entidad);
    }

    @Test
    public void updUsuario() throws Exception {
        UserWriterDTO writer = mock(UserWriterDTO.class);
        User entidad = mock(User.class);
        UserReaderDTO dto = mock(UserReaderDTO.class);

        when(writer.id()).thenReturn(1L);
        when(usuarioRepository.existsById(1L)).thenReturn(true);
        when(usuarioMapper.toEntity(writer)).thenReturn(entidad);
        when(usuarioRepository.save(entidad)).thenReturn(entidad);
        when(usuarioMapper.toDto(entidad)).thenReturn(dto);

        UserReaderDTO resultado = usuarioService.updUser(writer);

        assertSame(dto, resultado);
        verify(usuarioRepository).existsById(1L);
        verify(usuarioRepository).save(entidad);
    }

    @Test
    public void deleteUsuario() throws Exception{
        when(usuarioRepository.existsById(2L)).thenReturn(true);
        String msg = usuarioService.deleteUser(2L);

        assertTrue(msg.contains("Usuario eliminada con el ID: 2"));
        verify(usuarioRepository).existsById(2L);
        verify(usuarioRepository).deleteById(2L);
        
    }
    
}
