package com.perfumeria.aquadebelen.aquadebelen.model.productos.repository;

import com.perfumeria.aquadebelen.aquadebelen.model.productos.domain.TipoProducto;

public interface TipoProductoDAO {
    TipoProducto findById(Integer id);
}
