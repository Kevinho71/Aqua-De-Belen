package com.perfumeria.aquadebelen.aquadebelen.model.transaccion.repository;

import org.springframework.stereotype.Repository;

import com.perfumeria.aquadebelen.aquadebelen.model.transaccion.domain.Transaccion;

import jakarta.persistence.EntityManager;

@Repository
public class TransaccionDAOImpl implements TransaccionDAO {

    private EntityManager entityManager;

    public TransaccionDAOImpl(EntityManager entityManager){
        this.entityManager=entityManager;
    }

    @Override
    public void register(Transaccion transaccion) {
        entityManager.persist(transaccion);
    }

    @Override
    public void edit() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'edit'");
    }

    @Override
    public Transaccion findById() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public void delete() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }


}
