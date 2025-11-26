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

import jakarta.persistence.EntityManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.example.proyectate.feature.proyectos.Proyecto;
import com.example.proyectate.feature.proyectos.ProyectoMapper;
import com.example.proyectate.feature.proyectos.ProyectoReaderDTO;
import com.example.proyectate.feature.proyectos.ProyectoRepository;
import com.example.proyectate.feature.proyectos.ProyectoService;
import com.example.proyectate.feature.proyectos.ProyectoWriterDTO;
import com.example.proyectate.feature.users.User;


@ExtendWith(MockitoExtension.class)
public class ProyectoServiceTest {

    @Mock
    private ProyectoRepository proyectoRepository;

    @Mock
    private ProyectoMapper proyectoMapper;

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private ProyectoService proyectoService;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(proyectoService, "entityManager", entityManager);
    }

    @Test
    public void getAllProyecto() {
        Proyecto entidad = mock(Proyecto.class);
        ProyectoReaderDTO dto = mock(ProyectoReaderDTO.class);

        when(proyectoRepository.findAll()).thenReturn(List.of(entidad));
        when(proyectoMapper.toDto(entidad)).thenReturn(dto);

        List<ProyectoReaderDTO> resultado = proyectoService.getAllProyectos();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertSame(dto, resultado.get(0));

        verify(proyectoRepository).findAll();
        verify(proyectoMapper).toDto(entidad);
    }

    @Test
    public void getProyectoById() {
        Proyecto entidad = mock(Proyecto.class);
        ProyectoReaderDTO dto = mock(ProyectoReaderDTO.class);

        when(proyectoRepository.findById(1L)).thenReturn(Optional.of(entidad));
        when(proyectoMapper.toDto(entidad)).thenReturn(dto);

        ProyectoReaderDTO resultado = proyectoService.getProyectoById(1L);

        assertSame(dto, resultado);
        verify(proyectoRepository).findById(1L);
        verify(proyectoMapper).toDto(entidad);
    }

    @Test
    public void addProyecto() {
        ProyectoWriterDTO writer = mock(ProyectoWriterDTO.class);
        Proyecto entidad = mock(Proyecto.class);
        ProyectoReaderDTO dto = mock(ProyectoReaderDTO.class);
        User usuarioRef = mock(User.class);

        when(writer.idUsuario()).thenReturn(2L); 
        when(proyectoMapper.toEntity(writer)).thenReturn(entidad);
        when(entityManager.getReference(User.class, 2L)).thenReturn(usuarioRef); 
        when(proyectoRepository.save(entidad)).thenReturn(entidad);
        when(proyectoMapper.toDto(entidad)).thenReturn(dto);

        ProyectoReaderDTO resultado = proyectoService.addProyecto(writer);

        assertSame(dto, resultado);
        verify(proyectoMapper).toEntity(writer);
        verify(entityManager).getReference(User.class, 2L);
        verify(proyectoRepository).save(entidad);
        verify(proyectoMapper).toDto(entidad);
    }

    @Test
    public void updProyecto() throws Exception {
        ProyectoWriterDTO writer = mock(ProyectoWriterDTO.class);
        Proyecto entidad = mock(Proyecto.class);
        ProyectoReaderDTO dto = mock(ProyectoReaderDTO.class);
        User usuarioRef = mock(User.class);

        when(writer.id()).thenReturn(1L);
        when(writer.idUsuario()).thenReturn(1L); 
        when(proyectoRepository.existsById(1L)).thenReturn(true);
        when(proyectoMapper.toEntity(writer)).thenReturn(entidad);
        when(entityManager.getReference(User.class, 1L)).thenReturn(usuarioRef); 
        when(proyectoRepository.save(entidad)).thenReturn(entidad);
        when(proyectoMapper.toDto(entidad)).thenReturn(dto);

        ProyectoReaderDTO resultado = proyectoService.updProyecto(writer);

        assertSame(dto, resultado);
        verify(proyectoRepository).existsById(1L);
        verify(entityManager).getReference(User.class, 1L);
        verify(proyectoRepository).save(entidad);
    }
    
    @Test
    public void deleteProyecto() throws Exception{
        when(proyectoRepository.existsById(1L)).thenReturn(true);
        String msg = proyectoService.deleteProyecto(1L);

        assertTrue(msg.contains("proyecto eliminada con el ID: 1"));
        verify(proyectoRepository).existsById(1L);
        verify(proyectoRepository).deleteById(1L);
        
    }

}
