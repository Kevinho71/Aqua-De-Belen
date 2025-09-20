package com.perfumeria.aquadebelen.aquadebelen.api.products.dto;

public record ProductView(
    Integer id,
    String name,
    String brand,   // mapeado desde 'descripcion'
    double price,
    int stock,
    String category
) { }
