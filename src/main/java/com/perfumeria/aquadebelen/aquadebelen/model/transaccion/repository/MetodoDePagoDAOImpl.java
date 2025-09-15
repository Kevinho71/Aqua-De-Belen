package com.perfumeria.aquadebelen.aquadebelen.model.transaccion.repository;

import org.springframework.stereotype.Repository;

import com.perfumeria.aquadebelen.aquadebelen.model.transaccion.domain.MetodoDePago;

import jakarta.persistence.EntityManager;

@Repository
public class MetodoDePagoDAOImpl implements MetodoDePagoDAO{

    private EntityManager entityManager;

    public MetodoDePagoDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public MetodoDePago findById(Integer id) {
      return  entityManager.find(MetodoDePago.class, id);
    }


}
