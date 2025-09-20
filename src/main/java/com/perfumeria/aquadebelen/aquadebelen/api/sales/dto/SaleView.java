package com.perfumeria.aquadebelen.aquadebelen.api.sales.dto;

import java.time.LocalDateTime;

public record SaleView(
    Integer id,
    Integer productId,
    String productName,
    double quantity,
    double total,
    LocalDateTime date
) { }
