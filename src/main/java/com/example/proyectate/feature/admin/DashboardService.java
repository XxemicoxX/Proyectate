package com.example.proyectate.feature.admin;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.proyectate.feature.proyectos.ProyectoRepository;
import com.example.proyectate.feature.users.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashboardService {
    
    private final UserRepository userRepository;
    private final ProyectoRepository proyectoRepository;
    
    public DashboardStatsDTO getEstadisticas() {
        Long totalUsuarios = userRepository.count();
        Long totalProyectos = proyectoRepository.count();
        Long proyectosActivos = proyectoRepository.countByEstado("PROGRESO");
        Long proyectosCompletados = proyectoRepository.countByEstado("COMPLETADO");
        
        return DashboardStatsDTO.builder()
                .totalUsuarios(totalUsuarios)
                .totalProyectos(totalProyectos)
                .proyectosActivos(proyectosActivos != null ? proyectosActivos : 0L)
                .proyectosCompletados(proyectosCompletados != null ? proyectosCompletados : 0L)
                .build();
    }
    
    public List<ProyectoRecienteDTO> getProyectosRecientes() {
        return proyectoRepository.findTop10ByOrderByFechaInicioDesc()
                .stream()
                .limit(10)
                .map(proyecto -> ProyectoRecienteDTO.builder()
                        .id(proyecto.getId())
                        .nombre(proyecto.getNombre())
                        .descripcion(proyecto.getDescripcion())
                        .fechaInicio(proyecto.getFechaInicio())
                        .estado(proyecto.getEstado())
                        .nombreUsuario(proyecto.getIdUsuario() != null ? 
                                proyecto.getIdUsuario().getName() : "Sin asignar")
                        .emailUsuario(proyecto.getIdUsuario() != null ? 
                                proyecto.getIdUsuario().getEmail() : "")
                        .build())
                .collect(Collectors.toList());
    }
}
