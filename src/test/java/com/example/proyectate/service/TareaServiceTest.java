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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.example.proyectate.feature.etiquetas.Etiqueta;
import com.example.proyectate.feature.proyectos.Proyecto;
import com.example.proyectate.feature.tareas.Tarea;
import com.example.proyectate.feature.tareas.TareaMapper;
import com.example.proyectate.feature.tareas.TareaReaderDTO;
import com.example.proyectate.feature.tareas.TareaRepository;
import com.example.proyectate.feature.tareas.TareaService;
import com.example.proyectate.feature.tareas.TareaWriterDTO;


import jakarta.persistence.EntityManager;

@ExtendWith(MockitoExtension.class)
public class TareaServiceTest {
    @Mock
    private TareaRepository tareaRepository;

    @Mock
    private TareaMapper tareaMapper;

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private TareaService tareaService;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(tareaService, "entityManager", entityManager);
    }

    @Test
    public void getAllTarea() {
        Tarea entidad = mock(Tarea.class);
        TareaReaderDTO dto = mock(TareaReaderDTO.class);

        when(tareaRepository.findAll()).thenReturn(List.of(entidad));
        when(tareaMapper.toDto(entidad)).thenReturn(dto);

        List<TareaReaderDTO> resultado = tareaService.getAllTareas();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertSame(dto, resultado.get(0));

        verify(tareaRepository).findAll();
        verify(tareaMapper).toDto(entidad);
    }

    @Test
    public void getTareaById() {
        Tarea entidad = mock(Tarea.class);
        TareaReaderDTO dto = mock(TareaReaderDTO.class);

        when(tareaRepository.findById(1L)).thenReturn(Optional.of(entidad));
        when(tareaMapper.toDto(entidad)).thenReturn(dto);

        TareaReaderDTO resultado = tareaService.getTareaById(1L);

        assertSame(dto, resultado);
        verify(tareaRepository).findById(1L);
        verify(tareaMapper).toDto(entidad);
    }

    @Test
    public void addTarea() {
        TareaWriterDTO writer = mock(TareaWriterDTO.class);
        Tarea entidad = mock(Tarea.class);
        TareaReaderDTO dto = mock(TareaReaderDTO.class);
        Etiqueta etiquetaRef = mock(Etiqueta.class);
        Proyecto proyectoRef = mock(Proyecto.class);

        when(writer.idProyecto()).thenReturn(1L); 
        when(tareaMapper.toEntity(writer)).thenReturn(entidad);
        when(entityManager.getReference(Proyecto.class, 1L)).thenReturn(proyectoRef); 
        when(writer.idEtiqueta()).thenReturn(1L); 
        when(tareaMapper.toEntity(writer)).thenReturn(entidad);
        when(entityManager.getReference(Etiqueta.class, 1L)).thenReturn(etiquetaRef); 
        when(tareaRepository.save(entidad)).thenReturn(entidad);
        when(tareaMapper.toDto(entidad)).thenReturn(dto);

        TareaReaderDTO resultado = tareaService.addTarea(writer);

        assertSame(dto, resultado);
        verify(tareaMapper).toEntity(writer);
        verify(entityManager).getReference(Etiqueta.class, 1L);
        verify(tareaRepository).save(entidad);
        verify(tareaMapper).toDto(entidad);
    }

    @Test
    public void updTarea() throws Exception {
        TareaWriterDTO writer = mock(TareaWriterDTO.class);
        Tarea entidad = mock(Tarea.class);
        TareaReaderDTO dto = mock(TareaReaderDTO.class);
        Etiqueta etiquetaRef = mock(Etiqueta.class);
        Proyecto proyectoRef = mock(Proyecto.class);

        when(writer.idProyecto()).thenReturn(1L); 
        when(tareaMapper.toEntity(writer)).thenReturn(entidad);
        when(entityManager.getReference(Proyecto.class, 1L)).thenReturn(proyectoRef); 

        when(writer.id()).thenReturn(1L);
        when(writer.idEtiqueta()).thenReturn(1L); 
        when(tareaRepository.existsById(1L)).thenReturn(true);
        when(tareaMapper.toEntity(writer)).thenReturn(entidad);
        when(entityManager.getReference(Etiqueta.class, 1L)).thenReturn(etiquetaRef); 
        when(tareaRepository.save(entidad)).thenReturn(entidad);
        when(tareaMapper.toDto(entidad)).thenReturn(dto);

        TareaReaderDTO resultado = tareaService.updTarea(writer);

        assertSame(dto, resultado);
        verify(tareaRepository).existsById(1L);
        verify(entityManager).getReference(Etiqueta.class, 1L);
        verify(tareaRepository).save(entidad);
    }
    
    @Test
    public void deleteTarea() throws Exception{
        when(tareaRepository.existsById(1L)).thenReturn(true);
        String msg = tareaService.deleteTarea(1L);

        assertTrue(msg.contains("Tarea eliminada con el ID: 1"));
        verify(tareaRepository).existsById(1L);
        verify(tareaRepository).deleteById(1L);
        
    }
}
