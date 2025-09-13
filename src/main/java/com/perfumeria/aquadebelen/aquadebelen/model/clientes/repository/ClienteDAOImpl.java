package com.perfumeria.aquadebelen.aquadebelen.model.clientes.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.perfumeria.aquadebelen.aquadebelen.model.clientes.domain.Cliente;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;


@Repository
public class ClienteDAOImpl implements ClienteDAO {

    private EntityManager entityManager;

    @Autowired
    public ClienteDAOImpl(EntityManager entityManager){
        this.entityManager=entityManager;
    }

    @Transactional
    @Override
    public void save(Cliente cliente) {
        entityManager.persist(cliente);
    }

}
