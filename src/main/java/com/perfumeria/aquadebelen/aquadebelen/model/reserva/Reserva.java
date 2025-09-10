package com.perfumeria.aquadebelen.aquadebelen.domain.models.reserva;

import java.time.*;
import java.util.List;

import com.perfumeria.aquadebelen.aquadebelen.domain.models.clientes.Cliente;

public class Reserva {
    private Integer id;
    private LocalDateTime fecha_reserva;
    private LocalDateTime fecha_limite;
    private double adelanto;
    private Cliente cliente;
    private List<DetalleReserva> detalleReservas;
    private EstadoReserva estadoReserva;
}
