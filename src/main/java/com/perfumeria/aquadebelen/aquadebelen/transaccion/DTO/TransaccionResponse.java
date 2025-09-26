package com.perfumeria.aquadebelen.aquadebelen.transaccion.DTO;

public record TransaccionResponse(
     Integer transaccionId,
    double totalBruto,
    double descuento,
    double totalNeto,
    Boolean conFactura
) {
   
}
