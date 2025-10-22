package com.perfumeria.aquadebelen.aquadebelen.api.categories.dto;

public record CategoryCardDTO(
        int id,
        String nombre,
        int productos,
        int stockTotal,
        double ingresosBs
) {}
