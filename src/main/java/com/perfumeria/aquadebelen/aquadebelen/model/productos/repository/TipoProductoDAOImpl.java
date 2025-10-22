package com.perfumeria.aquadebelen.aquadebelen.model.productos.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.perfumeria.aquadebelen.aquadebelen.model.productos.domain.TipoProducto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class TipoProductoDAOImpl implements TipoProductoDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public TipoProducto findById(Integer id) {
        return em.find(TipoProducto.class, id);
    }

    @Override
    public List<TipoProducto> findAll() {
        return em.createQuery(
                "select t from TipoProducto t order by t.nombre",
                TipoProducto.class
        ).getResultList();
    }
}
