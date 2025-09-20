package com.perfumeria.aquadebelen.aquadebelen.api.stats;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.perfumeria.aquadebelen.aquadebelen.api.stats.dto.CategoryCardView;
import com.perfumeria.aquadebelen.aquadebelen.api.stats.dto.DashboardStatsResponse;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class StatsService {

    @PersistenceContext
    private EntityManager em;

    public DashboardStatsResponse dashboard(){
        int totalProducts = ((Number) em.createQuery("select count(p) from Producto p").getSingleResult()).intValue();

        int lowStockCount = ((Number) em.createQuery("""
            select count(p) from Producto p
            where (
              coalesce((select sum(s.cantidad) from Sublote s where s.producto.id = p.id),0) -
              coalesce((select sum(d.cantidad) from DetalleTransaccion d where d.producto.id = p.id),0)
            ) < 10
        """).getSingleResult()).intValue();

        double totalSalesAmount = ((Number) em.createQuery("""
            select coalesce(sum(t.monto),0) from Transaccion t
        """).getSingleResult()).doubleValue();

        var today = LocalDate.now();
        int todaysSalesCount = ((Number) em.createQuery("""
            select count(t) from Transaccion t
            where date(t.fecha) = :today
        """).setParameter("today", today).getSingleResult()).intValue();

        return new DashboardStatsResponse(totalProducts, lowStockCount, totalSalesAmount, todaysSalesCount);
    }

    public List<CategoryCardView> categories(){
        var ingresosRows = em.createQuery("""
            select p.tipoProducto.nombre, sum(d.cantidad * p.precio), count(distinct d.transaccion.id), sum(d.cantidad)
            from DetalleTransaccion d
            join d.producto p
            group by p.tipoProducto.nombre
        """, Object[].class).getResultList();

        var ingresoMap   = new java.util.HashMap<String, Double>();
        var ventasMap    = new java.util.HashMap<String, Integer>();
        var unidadesMap  = new java.util.HashMap<String, Integer>();
        for (Object[] r: ingresosRows){
            ingresoMap.put((String) r[0], ((Number) r[1]).doubleValue());
            ventasMap.put((String) r[0],  ((Number) r[2]).intValue());
            unidadesMap.put((String) r[0],((Number) r[3]).intValue());
        }

        var rows = em.createQuery("""
            select tp.nombre as categoria,
                   count(distinct p.id) as productos,
                   coalesce(sum(s.cantidad),0) as stockIn
            from Producto p
            left join p.tipoProducto tp
            left join Sublote s on s.producto.id = p.id
            group by tp.nombre
        """, Object[].class).getResultList();

        List<CategoryCardView> out = new ArrayList<>();
        for (Object[] r: rows){
            String cat = (String) r[0];
            int productos = ((Number) r[1]).intValue();
            int stockIn = ((Number) r[2]).intValue();
            int vendidos = unidadesMap.getOrDefault(cat, 0);
            int stockTotal = stockIn - vendidos;

            double valorInventario = ((Number) em.createQuery("""
                select coalesce(sum(p.precio * (
                  coalesce((select sum(s2.cantidad) from Sublote s2 where s2.producto.id = p.id),0) -
                  coalesce((select sum(d2.cantidad) from DetalleTransaccion d2 where d2.producto.id = p.id),0)
                )),0)
                from Producto p
                where p.tipoProducto.nombre = :cat
            """).setParameter("cat", cat).getSingleResult()).doubleValue();

            out.add(new CategoryCardView(
                cat,
                productos,
                stockTotal,
                ventasMap.getOrDefault(cat, 0),
                vendidos,
                valorInventario,
                ingresoMap.getOrDefault(cat, 0.0)
            ));
        }
        return out;
    }
}
