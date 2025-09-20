package com.perfumeria.aquadebelen.aquadebelen.core.inventory;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class InventoryQueryService {

    @PersistenceContext
    private EntityManager em;

    public int stockOf(Integer productoId) {
        Integer in = ((Number) em.createQuery(
            "select coalesce(sum(s.cantidad),0) from Sublote s where s.producto.id = :pid")
            .setParameter("pid", productoId)
            .getSingleResult()).intValue();

        Double out = ((Number) em.createQuery(
            "select coalesce(sum(d.cantidad),0) from DetalleTransaccion d where d.producto.id = :pid")
            .setParameter("pid", productoId)
            .getSingleResult()).doubleValue();

        return in - out.intValue();
    }

    public Map<Integer,Integer> stockFor(List<Integer> productIds){
        if(productIds.isEmpty()) return Map.of();
        List<Object[]> rows = em.createQuery("""
            select p.id,
                   coalesce(sum(s.cantidad),0),
                   coalesce((select sum(d.cantidad) from DetalleTransaccion d where d.producto.id = p.id),0)
            from Producto p
            left join Sublote s on s.producto.id = p.id
            where p.id in :ids
            group by p.id
        """, Object[].class)
        .setParameter("ids", productIds)
        .getResultList();

        return rows.stream().collect(Collectors.toMap(
            r -> (Integer) r[0],
            r -> ((Number) r[1]).intValue() - ((Number) r[2]).intValue()
        ));
    }
}
