package com.perfumeria.aquadebelen.aquadebelen.model.clientes.repository;

import com.perfumeria.aquadebelen.aquadebelen.model.clientes.domain.Cliente;

public interface ClienteDAO {

    void save(Cliente cliente);
    Cliente findById(Integer id);
}
