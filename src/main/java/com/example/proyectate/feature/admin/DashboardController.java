package com.example.proyectate.feature.admin;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/estadisticas")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<DashboardStatsDTO> getEstadisticas() {
        return ResponseEntity.ok(dashboardService.getEstadisticas());
    }

    @GetMapping("/proyectos-recientes")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<ProyectoRecienteDTO>> getProyectosRecientes() {
        return ResponseEntity.ok(dashboardService.getProyectosRecientes());
    }

    @PostConstruct
    public void init() {
        System.out.println("✔️ DashboardController CARGADO por Spring Boot");
    }

}
