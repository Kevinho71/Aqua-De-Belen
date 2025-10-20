package com.perfumeria.aquadebelen.aquadebelen.model.productos.repository;

import org.springframework.stereotype.Repository;

import com.perfumeria.aquadebelen.aquadebelen.model.productos.domain.Producto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class ProductoDAOImpl implements ProductoDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Producto save(Producto p) {
        em.persist(p);
        return p;
    }

    @Override
    public Producto findById(Integer id) {
        return em.find(Producto.class, id);
    }

    // ← NUEVO
    @Override
    public Integer nextId() {
        Number n = (Number) em.createNativeQuery("SELECT COALESCE(MAX(id),0)+1 FROM producto")
                              .getSingleResult();
        return n.intValue();
    }
}


/*package com.perfumeria.aquadebelen.aquadebelen.model.productos.repository;

import org.springframework.stereotype.Repository;

import com.perfumeria.aquadebelen.aquadebelen.model.productos.domain.Producto;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class ProductoDAOImpl implements ProductoDAO{
    private EntityManager entityManager;

    public ProductoDAOImpl(EntityManager entityManager){
        this.entityManager=entityManager;
    }

    @Override
    public Producto findById(Integer id) {
        return entityManager.find(Producto.class, id);
    }

    @Transactional
    @Override
    public void save(Producto producto) {
        entityManager.persist(producto);
    }
}*/
