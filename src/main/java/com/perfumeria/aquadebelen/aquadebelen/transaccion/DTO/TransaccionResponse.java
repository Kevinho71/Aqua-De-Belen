package com.perfumeria.aquadebelen.aquadebelen.transaccion.DTO;

import java.time.LocalDateTime;

public record TransaccionResponse(
     Integer transaccionId,
     String cliente,
    double totalBruto,
    double descuento,
    double totalNeto,
    Boolean conFactura,
    LocalDateTime fecha
) {
   
}
