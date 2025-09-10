package com.perfumeria.aquadebelen.aquadebelen.model.transaccion;

import java.time.LocalDateTime;
import java.util.List;

import com.perfumeria.aquadebelen.aquadebelen.model.clientes.Cliente;

public class Transaccion {
    private Integer id;
    private LocalDateTime fecha;
    private double monto;
    private double descuento;
    private String metodoDePago;
    private Cliente cliente;
    private List<DetalleTransaccion> detallesTransaccion;
}
