package com.perfumeria.aquadebelen.aquadebelen.model.transaccion.DTO;

public record DetalleTransaccionRequest(
    Integer productoId,
    double cantidad
) {

}
