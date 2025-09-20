package com.perfumeria.aquadebelen.aquadebelen.api.products.dto;

public record ProductUpdateRequest(
    String name,
    String brandOrDescription,
    Double price,
    Integer tipoProductoId
) { }
