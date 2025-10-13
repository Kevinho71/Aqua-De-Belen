package com.perfumeria.aquadebelen.aquadebelen.ventas.DTO;

import java.time.LocalDateTime;
import java.util.List;


public record VentaResponse(
     Integer ventaId,
     String cliente,
    double totalBruto,
    double descuentoTotal,
    double totalNeto,
    Boolean conFactura,
    LocalDateTime fecha,
    List<DetalleVentaResponse> detalles
) {
   
}
