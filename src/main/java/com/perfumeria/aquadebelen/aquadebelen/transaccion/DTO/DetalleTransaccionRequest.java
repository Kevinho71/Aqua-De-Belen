package com.perfumeria.aquadebelen.aquadebelen.transaccion.DTO;

public record DetalleTransaccionRequest(
    Integer productoId,
    double cantidad
) {

}
