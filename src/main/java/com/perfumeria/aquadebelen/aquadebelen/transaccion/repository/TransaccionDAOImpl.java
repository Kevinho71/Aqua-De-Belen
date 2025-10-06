package com.perfumeria.aquadebelen.aquadebelen.transaccion.repository;

import java.util.List;

import javax.management.Query;

import org.springframework.stereotype.Repository;

import com.perfumeria.aquadebelen.aquadebelen.transaccion.model.Transaccion;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class TransaccionDAOImpl implements TransaccionDAO {

    private EntityManager entityManager;

    public TransaccionDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public void store(Transaccion transaccion) {
        entityManager.merge(transaccion);
        entityManager.flush();
    }

    @Override
    public Transaccion findById(Integer id) {
        TypedQuery<Transaccion> query = entityManager.createQuery(
                "SELECT t FROM Transaccion t" + " JOIN FETCH t.detallesTransaccion" + " JOIN FETCH t.metodoDePago"
                        + " WHERE t.id =:data",
                Transaccion.class);
        query.setParameter("data", id);
        return query.getSingleResult();
    }

    @Transactional
    @Override
    public void deleteById(Integer id) {

        entityManager.createQuery("DELETE FROM DetalleTransaccion d WHERE d.transaccion.id =: data")
                .setParameter("data", id).executeUpdate();

        entityManager.createQuery("DELETE FROM Transaccion t WHERE t.id = :data").setParameter("data", id)
                .executeUpdate();
    }

    @Override
    public List<Transaccion> findALL() {
        TypedQuery<Transaccion> query = entityManager.createQuery("SELECT t FROM Transaccion t ORDER BY t.id ASC", Transaccion.class);
        List<Transaccion> lista = query.getResultList();
        return lista;
    }

    @Override
    public Integer nextId() {
        TypedQuery<Integer> query = entityManager.createQuery("SELECT COALESCE(MAX(t.id), 0) FROM Transaccion t ",
                Integer.class);
        return query.getSingleResult() + 1;

    }

}
