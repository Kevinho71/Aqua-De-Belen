package com.perfumeria.aquadebelen.aquadebelen.model.clientes.repository;

import org.springframework.stereotype.Repository;

import com.perfumeria.aquadebelen.aquadebelen.model.clientes.domain.Ubicacion;

import jakarta.persistence.EntityManager;

@Repository
public class UbicacionDAOImpl implements UbicacionDAO{

    private EntityManager entityManager;

    public UbicacionDAOImpl(EntityManager entityManager){
        this.entityManager=entityManager;
    }

    @Override
    public Ubicacion findById(Integer id) {
        return entityManager.find(Ubicacion.class, id);
    }


}
