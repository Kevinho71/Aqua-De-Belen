package com.perfumeria.aquadebelen.aquadebelen.model.clientes.repository;

import com.perfumeria.aquadebelen.aquadebelen.model.clientes.domain.NivelFidelidad;

public interface NivelFidelidadDAO {
    public void save(NivelFidelidad nivelFidelidad);
    public NivelFidelidad findById(Integer Id);
}
