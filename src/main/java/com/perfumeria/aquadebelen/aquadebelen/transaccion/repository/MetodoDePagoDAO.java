package com.perfumeria.aquadebelen.aquadebelen.transaccion.repository;

import com.perfumeria.aquadebelen.aquadebelen.transaccion.model.MetodoDePago;

public interface MetodoDePagoDAO {
    MetodoDePago findById(Integer id);
}
