package com.perfumeria.aquadebelen.aquadebelen.api.products.dto;

public record ProductListItemDTO(
        int id,
        String nombre,
        String marca,
        String categoria,
        double precio,
        int stock
) {}
