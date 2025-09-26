package com.perfumeria.aquadebelen.aquadebelen.productos.repository;

import org.springframework.stereotype.Repository;

import com.perfumeria.aquadebelen.aquadebelen.productos.model.TipoProducto;

import jakarta.persistence.EntityManager;

@Repository
public class TipoProductoDAOImpl implements TipoProductoDAO{
    private EntityManager entityManager;

    public TipoProductoDAOImpl(EntityManager entityManager){
        this.entityManager=entityManager;
    }

    @Override
    public TipoProducto findById(Integer id) {
       return entityManager.find(TipoProducto.class, id);
    }
}
