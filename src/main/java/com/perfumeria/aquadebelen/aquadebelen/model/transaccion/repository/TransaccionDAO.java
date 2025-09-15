package com.perfumeria.aquadebelen.aquadebelen.model.transaccion.repository;

import com.perfumeria.aquadebelen.aquadebelen.model.transaccion.domain.Transaccion;

public interface TransaccionDAO {
    void register (Transaccion transaccion);
    void edit();
    Transaccion findById();
    void delete();
}
