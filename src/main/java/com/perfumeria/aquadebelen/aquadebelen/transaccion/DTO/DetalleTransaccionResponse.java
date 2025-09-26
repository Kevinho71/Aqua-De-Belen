package com.perfumeria.aquadebelen.aquadebelen.transaccion.DTO;

public record DetalleTransaccionResponse(

    Integer transaccionId,
    Integer detalleId,
    String producto,
    double cantidad

) {

}
