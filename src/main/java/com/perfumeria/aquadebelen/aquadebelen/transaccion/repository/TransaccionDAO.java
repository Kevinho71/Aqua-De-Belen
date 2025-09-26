package com.perfumeria.aquadebelen.aquadebelen.transaccion.repository;

import java.util.List;

import com.perfumeria.aquadebelen.aquadebelen.transaccion.model.Transaccion;

public interface TransaccionDAO {
    void register (Transaccion transaccion);
    void edit(Transaccion transaccion);
    Transaccion findById(Integer id);
    void deleteById(Integer id);
    List<Transaccion> findALL();
}
