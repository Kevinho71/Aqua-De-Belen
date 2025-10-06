package com.perfumeria.aquadebelen.aquadebelen.transaccion.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.perfumeria.aquadebelen.aquadebelen.transaccion.model.DetalleTransaccion;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class DetalleTransaccionDAOImpl implements DetalleTransaccionDAO {

    private EntityManager entityManager;

    public DetalleTransaccionDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<DetalleTransaccion> buscarTransaccionesPorIdTransaccion(Integer id) {
        TypedQuery<DetalleTransaccion> query = entityManager
                .createQuery("SELECT d FROM DetalleTransaccion d WHERE d.transaccion.id = :data ", DetalleTransaccion.class);
        query.setParameter("data", id);
        List<DetalleTransaccion> lista = query.getResultList();
        return lista;
    }

}
