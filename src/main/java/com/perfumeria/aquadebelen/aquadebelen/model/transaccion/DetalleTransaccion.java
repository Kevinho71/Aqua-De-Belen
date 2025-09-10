package com.perfumeria.aquadebelen.aquadebelen.domain.models.transaccion;

import com.perfumeria.aquadebelen.aquadebelen.domain.models.Producto;

public class DetalleTransaccion {
    private Integer id;
    private double cantidad;
    private Producto producto;
    private Transaccion transaccion;
}
