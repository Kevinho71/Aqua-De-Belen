package com.perfumeria.aquadebelen.aquadebelen.transaccion.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.perfumeria.aquadebelen.aquadebelen.clientes.repository.ClienteDAO;
import com.perfumeria.aquadebelen.aquadebelen.productos.model.Producto;
import com.perfumeria.aquadebelen.aquadebelen.productos.repository.ProductoDAO;
import com.perfumeria.aquadebelen.aquadebelen.transaccion.DTO.DetalleTransaccionRequest;
import com.perfumeria.aquadebelen.aquadebelen.transaccion.DTO.DetalleTransaccionResponse;
import com.perfumeria.aquadebelen.aquadebelen.transaccion.DTO.TransaccionRequest;
import com.perfumeria.aquadebelen.aquadebelen.transaccion.DTO.TransaccionResponse;
import com.perfumeria.aquadebelen.aquadebelen.transaccion.model.DetalleTransaccion;
import com.perfumeria.aquadebelen.aquadebelen.transaccion.model.Transaccion;
import com.perfumeria.aquadebelen.aquadebelen.transaccion.repository.MetodoDePagoDAO;
import com.perfumeria.aquadebelen.aquadebelen.transaccion.repository.TransaccionDAO;

@Service
public class TransaccionService {
    private final TransaccionDAO tDAO;
    private final MetodoDePagoDAO mpDAO;
    private final ClienteDAO cDAO;
    private final ProductoDAO pDAO;

    public TransaccionService(TransaccionDAO tDAO, MetodoDePagoDAO mpDAO, ClienteDAO cDAO, ProductoDAO pDAO) {
        this.tDAO = tDAO;
        this.mpDAO = mpDAO;
        this.cDAO = cDAO;
        this.pDAO = pDAO;

    }

    public TransaccionResponse store(Integer id, TransaccionRequest req) {
        Transaccion transaccion = new Transaccion();
        if (id == null) {
            transaccion.setId(tDAO.nextId());
            transaccion.setCliente(cDAO.findById(req.clienteId()));
            transaccion.setFecha(LocalDateTime.now());
            transaccion.setTotalBruto(calcularTotalBruto(req.detalles()));
            transaccion.setMetodoDePago(mpDAO.findById(req.metodoDePagoId()));
            transaccion.setConFactura(req.conFactura());
            agregarDetalles(req.detalles(), transaccion);
            transaccion.setTotalNeto(transaccion.getTotalBruto() - transaccion.getDescuentoTotal());
            tDAO.store(transaccion);
        } else {
            transaccion = tDAO.findById(id);
            transaccion.setCliente(cDAO.findById(req.clienteId()));
            transaccion.setMetodoDePago(mpDAO.findById(req.metodoDePagoId()));
            transaccion.setTotalBruto(calcularTotalBruto(req.detalles()));
            transaccion.setConFactura(req.conFactura());
            actualizarDetalles(req.detalles(), transaccion);
            transaccion.setTotalNeto(transaccion.getTotalBruto() - transaccion.getDescuentoTotal());
            tDAO.store(transaccion);
        }
        Transaccion transaccion2 = tDAO.findById(transaccion.getId());
        return mapToDtoResponse(transaccion2);
    }

    public double calcularTotalBruto(List<DetalleTransaccionRequest> detalles) {
        double totalBruto = 0;
        for (DetalleTransaccionRequest dt : detalles) {

            // AQUI CREAMOS UN NUEVO PRODUCTO CON EL FIN DE HALLAR SU PRECIO Y VAMOS
            // CALCULANDO EL TOTAL BRUTO
            Producto producto = pDAO.findById(dt.productoId());
            double subtotal = producto.getPrecio() * dt.cantidad();
            totalBruto = totalBruto + subtotal;
        }
        return totalBruto;
    }

    public void agregarDetalles(List<DetalleTransaccionRequest> detalles, Transaccion transaccion) {

        double descuentoTotal=0;
        for (DetalleTransaccionRequest dt : detalles) {
            DetalleTransaccion detalle = new DetalleTransaccion();
            Producto producto = pDAO.findById(dt.productoId());
            detalle.setCantidad(dt.cantidad());
            detalle.setProducto(producto);
            detalle.setDescuento(dt.descuento());
            double subtotal = (producto.getPrecio() * dt.cantidad())-dt.descuento();
            detalle.setSubtotal(subtotal);
            descuentoTotal=descuentoTotal+dt.descuento();
            transaccion.addDetalle(detalle);
        }
        transaccion.setDescuentoTotal(descuentoTotal);
    }

    public void actualizarDetalles(List<DetalleTransaccionRequest> detalles, Transaccion transaccion) {
        transaccion.getDetallesTransaccion().clear();
        agregarDetalles(detalles, transaccion);

    }

    // BORRAR UNA TRANSACCION
    public void borrar(Integer id) {
        tDAO.deleteById(id);
    }

    // BUSCAR UNA TRANSACCION
    public TransaccionResponse buscar(Integer id) {
        Transaccion transaccion = tDAO.findById(id);
        return mapToDtoResponse(transaccion);
    }

    public List<TransaccionResponse> listar() {
        List<Transaccion> lista = tDAO.findALL();
        List<TransaccionResponse> listaResp = new ArrayList<>();
        for (Transaccion t : lista) {
            TransaccionResponse e = mapToDtoResponse(t);
            listaResp.add(e);
        }
        return listaResp;
    }

    public TransaccionResponse mapToDtoResponse(Transaccion transaccion) {
        List<DetalleTransaccionResponse> listResp = new ArrayList<>();
        for (DetalleTransaccion dt : transaccion.getDetallesTransaccion()) {
            DetalleTransaccionResponse dtr = new DetalleTransaccionResponse(dt.getTransaccion().getId(), dt.getId(),
                    dt.getProducto().getNombre(), dt.getProducto().getPrecio(), dt.getCantidad(), dt.getDescuento(), dt.getSubtotal());
            listResp.add(dtr);
        }
        return new TransaccionResponse(transaccion.getId(),
                transaccion.getCliente().getNombre() + " " + transaccion.getCliente().getApellido(),
                transaccion.getTotalBruto(), transaccion.getDescuentoTotal(), transaccion.getTotalNeto(),
                transaccion.isConFactura(), transaccion.getFecha(), listResp);
    }

    public List<DetalleTransaccionResponse> listarDetalles(Integer id) {
        return null;
    }

}
