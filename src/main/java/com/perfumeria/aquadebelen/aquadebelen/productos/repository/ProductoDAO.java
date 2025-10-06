package com.perfumeria.aquadebelen.aquadebelen.productos.repository;

import com.perfumeria.aquadebelen.aquadebelen.productos.model.Producto;

public interface ProductoDAO {
    Producto findById(Integer id);
    void store(Producto producto);
}
