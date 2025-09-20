package com.perfumeria.aquadebelen.aquadebelen.api.products.dto;

public record ProductCreateRequest(
    String name,
    String brandOrDescription,
    double price,
    Integer tipoProductoId
) { }
