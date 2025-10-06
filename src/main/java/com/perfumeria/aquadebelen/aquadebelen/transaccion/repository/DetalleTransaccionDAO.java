package com.perfumeria.aquadebelen.aquadebelen.transaccion.repository;

import java.util.List;

import com.perfumeria.aquadebelen.aquadebelen.transaccion.model.DetalleTransaccion;

public interface DetalleTransaccionDAO {
    List<DetalleTransaccion> buscarTransaccionesPorIdTransaccion(Integer id);
}
