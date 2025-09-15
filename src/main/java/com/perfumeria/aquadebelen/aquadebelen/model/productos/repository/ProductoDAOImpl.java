package com.perfumeria.aquadebelen.aquadebelen.model.productos.repository;

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
}
