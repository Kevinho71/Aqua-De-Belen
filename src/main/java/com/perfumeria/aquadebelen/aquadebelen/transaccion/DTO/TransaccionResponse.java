package com.perfumeria.aquadebelen.aquadebelen.transaccion.DTO;

import java.time.LocalDateTime;
import java.util.List;


public record TransaccionResponse(
     Integer transaccionId,
     String cliente,
    double totalBruto,
    double descuentoTotal,
    double totalNeto,
    Boolean conFactura,
    LocalDateTime fecha,
    List<DetalleTransaccionResponse> detalles
) {
   
}
