package com.perfumeria.aquadebelen.aquadebelen.api.categories;

import java.util.List;

import org.springframework.stereotype.Service;

import com.perfumeria.aquadebelen.aquadebelen.api.categories.dto.CategoryCardDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final EntityManager em;

    public List<CategoryCardDTO> cards() {
        // productos por categoría, stock total, ventas (ingresos) por categoría
        List<Tuple> rows = em.createQuery("""
                select tp.id as id,
                       tp.nombre as nombre,
                       count(distinct p.id) as productos,
                       coalesce(sum(s.cantidad),0) as stockTotal,
                       coalesce(sum(d.cantidad * p.precio),0) as ingresos
                from TipoProducto tp
                left join Producto p on p.tipoProducto = tp
                left join Sublote s on s.producto = p
                left join DetalleTransaccion d on d.producto = p
                group by tp.id, tp.nombre
                order by tp.nombre
                """, Tuple.class).getResultList();

        return rows.stream().map(r -> new CategoryCardDTO(
                ((Number) r.get("id")).intValue(),
                r.get("nombre", String.class),
                ((Number) r.get("productos")).intValue(),
                ((Number) r.get("stockTotal")).intValue(),
                ((Number) r.get("ingresos")).doubleValue()
        )).toList();
    }
}
