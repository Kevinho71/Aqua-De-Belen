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

    /**
     * Inserta producto asignando el ID manualmente (MAX(id)+1) para
     * evitar el error 23505 cuando la secuencia del motor no coincide.
     */
    @Transactional
    public Integer create(ProductCreateRequest req){
        // Validaciones mínimas (opcional)
        if (req == null) throw new IllegalArgumentException("Request nulo");
        if (req.tipoProductoId() == null) throw new IllegalArgumentException("tipoProductoId es requerido");

        // Cargar TipoProducto
        TipoProducto tp = tipoProductoDAO.findById(req.tipoProductoId());
        if (tp == null) throw new IllegalArgumentException("TipoProducto no encontrado: " + req.tipoProductoId());

        // Construir la entidad alineada a tus columnas (descripcion, nombre, precio, tipo_producto_id)
        Producto p = new Producto(req.price(), req.brandOrDescription(), req.name(), tp);

        // 🔸 Asignar ID manual antes de persistir (clave del paso 3)
        p.setId(productoDAO.nextId());

        // Persistir
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
        if (req.tipoProductoId() != null) {
            TipoProducto tp = tipoProductoDAO.findById(req.tipoProductoId());
            if (tp == null) throw new IllegalArgumentException("TipoProducto no encontrado: " + req.tipoProductoId());
            p.setTipoProducto(tp);
        }
        em.merge(p);
    }

    @Transactional
    public void delete(Integer id){
        Producto p = productoDAO.findById(id);
        if (p != null) em.remove(p);
    }
}


/*package com.perfumeria.aquadebelen.aquadebelen.api.products;

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
}*/
