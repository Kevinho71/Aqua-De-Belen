package com.perfumeria.aquadebelen.aquadebelen.api.dashboard.dto;

public record DashboardResumenDTO(
        long totalProductos,
        long stockBajo,
        double ventasTotales,
        long ventasHoy
) {}
