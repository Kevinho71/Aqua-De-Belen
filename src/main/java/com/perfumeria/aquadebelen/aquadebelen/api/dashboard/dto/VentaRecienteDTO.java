package com.perfumeria.aquadebelen.aquadebelen.api.dashboard.dto;

import java.time.LocalDateTime;

public record VentaRecienteDTO(
        int transaccionId,
        String productoNombreEjemplo,
        int unidades,
        double total,
        LocalDateTime fecha
) {}
