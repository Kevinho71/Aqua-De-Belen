package com.perfumeria.aquadebelen.aquadebelen.api.stats.dto;

public record DashboardStatsResponse(
    int totalProducts,
    int lowStockCount,
    double totalSalesAmount,
    int todaysSalesCount
) { }
