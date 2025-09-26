package com.perfumeria.aquadebelen.aquadebelen.clientes.repository;

import com.perfumeria.aquadebelen.aquadebelen.clientes.model.Cliente;

public interface ClienteDAO {

    void save(Cliente cliente);
    Cliente findById(Integer id);
}
