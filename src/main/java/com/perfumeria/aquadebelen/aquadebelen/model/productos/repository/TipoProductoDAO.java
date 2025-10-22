package com.perfumeria.aquadebelen.aquadebelen.model.productos.repository;

import java.util.List;

import com.perfumeria.aquadebelen.aquadebelen.model.productos.domain.TipoProducto;

public interface TipoProductoDAO {
    TipoProducto findById(Integer id);

    // NUEVO: para listar categorías en el frontend
    List<TipoProducto> findAll();
}
