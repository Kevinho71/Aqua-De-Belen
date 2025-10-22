package com.perfumeria.aquadebelen.aquadebelen.api.products;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perfumeria.aquadebelen.aquadebelen.api.products.dto.ProductListItemDTO;
import com.perfumeria.aquadebelen.aquadebelen.model.productos.domain.TipoProducto;
import com.perfumeria.aquadebelen.aquadebelen.model.productos.repository.TipoProductoDAO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductReadController {

    private final ProductReadService service;
    private final TipoProductoDAO tipoProductoDAO;

    @GetMapping
    public List<ProductListItemDTO> list() {
        return service.list();
    }

    @GetMapping("/categorias")
    public List<TipoProducto> categorias() {
        return tipoProductoDAO.findAll();
    }
}
