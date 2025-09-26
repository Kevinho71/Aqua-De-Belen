package com.perfumeria.aquadebelen.aquadebelen.clientes.repository;

import org.springframework.stereotype.Repository;

import com.perfumeria.aquadebelen.aquadebelen.clientes.model.NivelFidelidad;

import jakarta.persistence.EntityManager;

@Repository
public class NivelFidelidadDAOImpl implements NivelFidelidadDAO {

    private EntityManager entityManager;

    public NivelFidelidadDAOImpl(EntityManager entityManager){
        this.entityManager=entityManager;
    }

    @Override
    public void save(NivelFidelidad nivelFidelidad) {
        entityManager.persist(nivelFidelidad);
    }

    @Override
    public NivelFidelidad findById(Integer Id) {
       return entityManager.find(NivelFidelidad.class, Id);
    }


}
