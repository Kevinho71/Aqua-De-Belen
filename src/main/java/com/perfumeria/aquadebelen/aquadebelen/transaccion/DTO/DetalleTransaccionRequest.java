package com.perfumeria.aquadebelen.aquadebelen.transaccion.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record DetalleTransaccionRequest(
    @NotNull
    Integer productoId,

    @NotNull
    @Min(1)
    double cantidad,
    
    @NotNull
    @Min(0)
    double descuento
) {

}
