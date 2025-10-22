package com.perfumeria.aquadebelen.aquadebelen.api.dashboard;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.perfumeria.aquadebelen.aquadebelen.api.dashboard.dto.DashboardResumenDTO;
import com.perfumeria.aquadebelen.aquadebelen.api.dashboard.dto.VentaRecienteDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final EntityManager em;

    public DashboardResumenDTO resumen() {
        long totalProductos = em.createQuery("select count(p) from Producto p", Long.class)
                .getSingleResult();

        long stockBajo = em.createQuery("""
                select count(distinct p.id)
                from Sublote s
                join s.producto p
                group by p.id
                having coalesce(sum(s.cantidad),0) < 5
                """, Long.class)
                .getResultStream().mapToLong(x -> x).sum();

        // ventas totales (no negativas)
        Double ventasTotales = em.createQuery("""
                select coalesce(sum(t.monto),0)
                from Transaccion t
                """, Double.class).getSingleResult();
        if (ventasTotales == null) ventasTotales = 0d;
        if (ventasTotales < 0) ventasTotales = 0d; // clamp para evitar negativos en pantalla

        long ventasHoy = em.createQuery("""
                select count(t)
                from Transaccion t
                where date(t.fecha) = :hoy
                """, Long.class)
                .setParameter("hoy", LocalDate.now())
                .getSingleResult();

        return new DashboardResumenDTO(totalProductos, stockBajo, ventasTotales, ventasHoy);
    }

    public List<VentaRecienteDTO> ventasRecientes(int limit) {
        // Por transacción: nombre del primer producto (o "Varios"), cantidad total y total
        List<Tuple> rows = em.createQuery("""
                select t.id as id,
                       t.fecha as fecha,
                       sum(d.cantidad) as unidades,
                       sum(d.cantidad * p.precio) as total,
                       min(p.nombre) as ejemplo
                from DetalleTransaccion d
                join d.transaccion t
                join d.producto p
                group by t.id, t.fecha
                order by t.fecha desc
                """, Tuple.class)
                .setMaxResults(limit)
                .getResultList();

        return rows.stream().map(r -> {
            String nombre = r.get("ejemplo", String.class);
            long unidades = ((Number) r.get("unidades")).longValue();
            if (unidades > 1) nombre = nombre + " (+)";
            return new VentaRecienteDTO(
                ((Number) r.get("id")).intValue(),
                nombre,
                ((Number) r.get("unidades")).intValue(),
                ((Number) r.get("total")).doubleValue(),
                r.get("fecha", java.time.LocalDateTime.class)
            );
        }).toList();
    }
}
