package com.perfumeria.aquadebelen.aquadebelen.model.clientes.repository;

import com.perfumeria.aquadebelen.aquadebelen.model.clientes.domain.Ubicacion;

public interface UbicacionDAO {
    public Ubicacion findById(Integer id);
}
