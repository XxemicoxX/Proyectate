package com.example.proyectate.feature.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DashboardStatsDTO {
    private Long totalUsuarios;
    private Long totalProyectos;
    private Long proyectosProgeso;
    private Long proyectosCompletados;
}
