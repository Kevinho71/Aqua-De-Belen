package com.perfumeria.aquadebelen.aquadebelen.api.stats.dto;

public record CategoryCardView(
    String category,
    int products,
    int stockTotal,
    int ventas,
    int unidadesVendidas,
    double valorInventario,
    double ingresos
) { }
