package com.perfumeria.aquadebelen.aquadebelen.clientes.repository;

import com.perfumeria.aquadebelen.aquadebelen.clientes.model.NivelFidelidad;

public interface NivelFidelidadDAO {
    public void save(NivelFidelidad nivelFidelidad);
    public NivelFidelidad findById(Integer Id);
}
