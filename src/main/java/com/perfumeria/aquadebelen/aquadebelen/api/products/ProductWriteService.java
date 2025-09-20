package com.perfumeria.aquadebelen.aquadebelen.api.products;

import org.springframework.stereotype.Service;

import com.perfumeria.aquadebelen.aquadebelen.api.products.dto.ProductCreateRequest;
import com.perfumeria.aquadebelen.aquadebelen.api.products.dto.ProductUpdateRequest;
import com.perfumeria.aquadebelen.aquadebelen.model.productos.domain.Producto;
import com.perfumeria.aquadebelen.aquadebelen.model.productos.domain.TipoProducto;
import com.perfumeria.aquadebelen.aquadebelen.model.productos.repository.ProductoDAO;
import com.perfumeria.aquadebelen.aquadebelen.model.productos.repository.TipoProductoDAO;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Service
public class ProductWriteService {

    private final ProductoDAO productoDAO;
    private final TipoProductoDAO tipoProductoDAO;
    private final EntityManager em;

    public ProductWriteService(ProductoDAO productoDAO, TipoProductoDAO tipoProductoDAO, EntityManager em){
        this.productoDAO = productoDAO;
        this.tipoProductoDAO = tipoProductoDAO;
        this.em = em;
    }

    @Transactional
    public Integer create(ProductCreateRequest req){
        TipoProducto tp = tipoProductoDAO.findById(req.tipoProductoId());
        Producto p = new Producto(req.price(), req.brandOrDescription(), req.name(), tp);
        productoDAO.save(p);
        return p.getId();
    }

    @Transactional
    public void update(Integer id, ProductUpdateRequest req){
        Producto p = productoDAO.findById(id);
        if (p == null) throw new IllegalArgumentException("Producto no encontrado");
        if (req.name() != null) p.setNombre(req.name());
        if (req.brandOrDescription() != null) p.setDescripcion(req.brandOrDescription());
        if (req.price() != null) p.setPrecio(req.price());
        if (req.tipoProductoId() != null) p.setTipoProducto(tipoProductoDAO.findById(req.tipoProductoId()));
        em.merge(p);
    }

    @Transactional
    public void delete(Integer id){
        Producto p = productoDAO.findById(id);
        if (p != null) em.remove(p);
    }
}
