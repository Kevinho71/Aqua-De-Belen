package com.perfumeria.aquadebelen.aquadebelen.api.products;

import java.util.List;

import org.springframework.stereotype.Service;

import com.perfumeria.aquadebelen.aquadebelen.api.products.dto.ProductListItemDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductReadService {

    private final EntityManager em;

    public List<ProductListItemDTO> list() {
        List<Tuple> rows = em.createQuery("""
                select p.id as id,
                       p.nombre as nombre,
                       p.descripcion as marca,
                       p.precio as precio,
                       tp.nombre as categoria,
                       coalesce(sum(s.cantidad),0) as stock
                from Producto p
                left join p.tipoProducto tp
                left join Sublote s on s.producto = p
                group by p.id, p.nombre, p.descripcion, p.precio, tp.nombre
                order by p.nombre
                """, Tuple.class).getResultList();

        return rows.stream().map(r -> new ProductListItemDTO(
                ((Number) r.get("id")).intValue(),
                r.get("nombre", String.class),
                r.get("marca", String.class),
                r.get("categoria", String.class),
                ((Number) r.get("precio")).doubleValue(),
                ((Number) r.get("stock")).intValue()
        )).toList();
    }
}
