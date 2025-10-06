package com.perfumeria.aquadebelen.aquadebelen.transaccion.DTO;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record TransaccionRequest(

    @NotNull
    Integer clienteId,

    @NotNull
    Integer metodoDePagoId,

    boolean conFactura,

    @NotNull
    @NotEmpty
    @Valid
    List<DetalleTransaccionRequest> detalles

) {



}
