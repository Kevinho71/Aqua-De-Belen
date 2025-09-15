package com.perfumeria.aquadebelen.aquadebelen.model.transaccion.repository;

import com.perfumeria.aquadebelen.aquadebelen.model.transaccion.domain.MetodoDePago;

public interface MetodoDePagoDAO {
    MetodoDePago findById(Integer id);
}
