package com.perfumeria.aquadebelen.aquadebelen.transaccion.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.perfumeria.aquadebelen.aquadebelen.transaccion.model.Transaccion;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class TransaccionDAOImpl implements TransaccionDAO {

    private EntityManager entityManager;

    public TransaccionDAOImpl(EntityManager entityManager){
        this.entityManager=entityManager;
    }

    @Transactional
    @Override
    public void register(Transaccion transaccion) {
       
        entityManager.persist(transaccion);
    }

    @Transactional
    @Override
    public void edit(Transaccion transaccion) {
        entityManager.merge(transaccion);
    }

    @Override
    public Transaccion findById(Integer id) {
        return entityManager.find(Transaccion.class, id);
    }

    @Transactional
    @Override
    public void deleteById(Integer id) {
        Transaccion transaccion= entityManager.find(Transaccion.class, id);
        entityManager.remove(transaccion);
    }

    @Override
    public List<Transaccion> findALL() {
         TypedQuery<Transaccion> query = entityManager.createQuery("SELECT t FROM Transaccion t" , Transaccion.class);
         List<Transaccion> lista= query.getResultList();
         return lista;
    }

    @Override
    public Integer nextId() {
        TypedQuery<Integer> query = entityManager.createQuery("SELECT COALESCE(MAX(t.id), 0) FROM Transaccion t ", Integer.class);
        return query.getSingleResult()+1;
 
    }

}
