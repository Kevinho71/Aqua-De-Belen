package com.perfumeria.aquadebelen.aquadebelen.model.transaccion.DTO;

import java.time.LocalDateTime;
import java.util.List;

public record TransaccionRequest(
    Integer clienteId,
    double descuento,
    LocalDateTime fecha,
    Integer metodoDePagoId,
    boolean conFactura,
    List<DetalleTransaccionRequest> detalles,
    FacturaRequest factura
) {



}
