package com.perfumeria.aquadebelen.aquadebelen.ventas.repository;

import java.util.List;

import com.perfumeria.aquadebelen.aquadebelen.ventas.model.DetalleVenta;

public interface DetalleVentaDAO {
    List<DetalleVenta> buscarTransaccionesPorIdTransaccion(Integer id);
}
