package com.perfumeria.aquadebelen.aquadebelen.model.productos.repository;

import com.perfumeria.aquadebelen.aquadebelen.model.productos.domain.Producto;

public interface ProductoDAO {
    Producto save(Producto p);
    Producto findById(Integer id);

    // ← NUEVO
    Integer nextId();
}


/*package com.perfumeria.aquadebelen.aquadebelen.model.productos.repository;

import com.perfumeria.aquadebelen.aquadebelen.model.productos.domain.Producto;

public interface ProductoDAO {
    Producto findById(Integer id);
    void save(Producto producto);
}*/
