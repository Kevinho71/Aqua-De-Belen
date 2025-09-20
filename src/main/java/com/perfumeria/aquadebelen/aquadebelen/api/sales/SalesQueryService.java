package com.perfumeria.aquadebelen.aquadebelen.api.sales;

import java.util.List;

import org.springframework.stereotype.Service;

import com.perfumeria.aquadebelen.aquadebelen.api.sales.dto.SaleView;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class SalesQueryService {

    @PersistenceContext
    private EntityManager em;

    public List<SaleView> listAll(){
        var rows = em.createQuery("""
            select d.id, d.producto.id, d.producto.nombre, d.cantidad,
                   (d.cantidad * d.producto.precio),
                   d.transaccion.fecha
            from DetalleTransaccion d
            order by d.transaccion.fecha desc, d.id desc
        """, Object[].class).getResultList();

        return rows.stream().map(r -> new SaleView(
            (Integer) r[0],
            (Integer) r[1],
            (String)  r[2],
            ((Number) r[3]).doubleValue(),
            ((Number) r[4]).doubleValue(),
            (java.time.LocalDateTime) r[5]
        )).toList();
    }
}
