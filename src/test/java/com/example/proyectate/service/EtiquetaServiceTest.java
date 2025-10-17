package com.example.proyectate.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.proyectate.feature.etiquetas.Etiqueta;
import com.example.proyectate.feature.etiquetas.EtiquetaMapper;
import com.example.proyectate.feature.etiquetas.EtiquetaReaderDTO;
import com.example.proyectate.feature.etiquetas.EtiquetaRepository;
import com.example.proyectate.feature.etiquetas.EtiquetaService;
import com.example.proyectate.feature.etiquetas.EtiquetaWriterDTO;

@ExtendWith(MockitoExtension.class)
public class EtiquetaServiceTest {

    @Mock
    private EtiquetaRepository etiquetaRepository;

    @Mock
    private EtiquetaMapper etiquetaMapper;

    @InjectMocks
    private EtiquetaService etiquetaService;

    @Test
    public void getAllEtiquetas() {
        Etiqueta entidad = mock(Etiqueta.class);
        EtiquetaReaderDTO dto = mock(EtiquetaReaderDTO.class);

        when(etiquetaRepository.findAll()).thenReturn(List.of(entidad));
        when(etiquetaMapper.toDto(entidad)).thenReturn(dto);

        List<EtiquetaReaderDTO> resultado = etiquetaService.getAllEtiquetas();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertSame(dto, resultado.get(0));

        verify(etiquetaRepository).findAll();
        verify(etiquetaMapper).toDto(entidad);
    }

    @Test
    public void getEtiquetaById() {
        Etiqueta entidad = mock(Etiqueta.class);
        EtiquetaReaderDTO dto = mock(EtiquetaReaderDTO.class);

        when(etiquetaRepository.findById(1L)).thenReturn(Optional.of(entidad));
        when(etiquetaMapper.toDto(entidad)).thenReturn(dto);

        EtiquetaReaderDTO resultado = etiquetaService.getEtiquetaById(1L);

        assertSame(dto, resultado);
        verify(etiquetaRepository).findById(1L);
        verify(etiquetaMapper).toDto(entidad);
    }

    @Test
    public void addEtiqueta() {
        EtiquetaWriterDTO writer = mock(EtiquetaWriterDTO.class);
        Etiqueta entidad = mock(Etiqueta.class);
        EtiquetaReaderDTO dto = mock(EtiquetaReaderDTO.class);

        when(etiquetaMapper.toEntity(writer)).thenReturn(entidad);
        when(etiquetaRepository.save(entidad)).thenReturn(entidad);
        when(etiquetaMapper.toDto(entidad)).thenReturn(dto);

        EtiquetaReaderDTO resultado = etiquetaService.addEtiqueta(writer);

        assertSame(dto, resultado);
        verify(etiquetaMapper).toEntity(writer);
        verify(etiquetaRepository).save(entidad);
        verify(etiquetaMapper).toDto(entidad);
    }

    @Test
    public void updEtiqueta() throws Exception {
        EtiquetaWriterDTO writer = mock(EtiquetaWriterDTO.class);
        Etiqueta entidad = mock(Etiqueta.class);
        EtiquetaReaderDTO dto = mock(EtiquetaReaderDTO.class);

        when(writer.id()).thenReturn(1L);
        when(etiquetaRepository.existsById(1L)).thenReturn(true);
        when(etiquetaMapper.toEntity(writer)).thenReturn(entidad);
        when(etiquetaRepository.save(entidad)).thenReturn(entidad);
        when(etiquetaMapper.toDto(entidad)).thenReturn(dto);

        EtiquetaReaderDTO resultado = etiquetaService.updEtiqueta(writer);

        assertSame(dto, resultado);
        verify(etiquetaRepository).existsById(1L);
        verify(etiquetaRepository).save(entidad);
    }

   @Test
    public void deleteEtiqueta() throws Exception {
        when(etiquetaRepository.existsById(1L)).thenReturn(true);

        String msg = etiquetaService.deleteEtiqueta(1L);

        assertTrue(msg.contains("Etiqueta eliminada con el ID: 1"));
        verify(etiquetaRepository).existsById(1L);
        verify(etiquetaRepository).deleteById(1L);
    }
    

}
