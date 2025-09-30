package com.perfumeria.aquadebelen.aquadebelen.transaccion.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.perfumeria.aquadebelen.aquadebelen.clientes.repository.ClienteDAO;
import com.perfumeria.aquadebelen.aquadebelen.productos.model.Producto;
import com.perfumeria.aquadebelen.aquadebelen.productos.repository.ProductoDAO;
import com.perfumeria.aquadebelen.aquadebelen.transaccion.DTO.DetalleTransaccionRequest;
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


      public TransaccionResponse store(TransaccionRequest req) {
        Transaccion transaccion = new Transaccion();
        if (req.transaccionId() == null) {
            transaccion.setId(tDAO.nextId());
            transaccion.setCliente(cDAO.findById(req.clienteId()));
            transaccion.setFecha(LocalDateTime.now());
            transaccion.setDescuento(req.descuento());
            transaccion.setTotalBruto(calcularTotalBruto(req.detalles()));
            transaccion.setTotalNeto(transaccion.getTotalBruto() - transaccion.getDescuento());
            transaccion.setMetodoDePago(mpDAO.findById(req.metodoDePagoId()));
            transaccion.setConFactura(req.conFactura());
            agregarDetalles(req.detalles(), transaccion);
            tDAO.store(transaccion);
        } else {
            transaccion = tDAO.findById(req.transaccionId());
            transaccion.setCliente(cDAO.findById(req.clienteId()));
            transaccion.setMetodoDePago(mpDAO.findById(req.metodoDePagoId()));
            transaccion.setDescuento(req.descuento());
            transaccion.setTotalBruto(calcularTotalBruto(req.detalles()));
            transaccion.setTotalNeto(transaccion.getTotalBruto() - transaccion.getDescuento());
            transaccion.setConFactura(req.conFactura());
            actualizarDetalles(req.detalles(), transaccion);
            tDAO.store(transaccion);
        }
        return new TransaccionResponse(transaccion.getId(),transaccion.getCliente().getNombre(), transaccion.getTotalBruto(), transaccion.getDescuento(),
                transaccion.getTotalNeto(), transaccion.isConFactura(), transaccion.getFecha());
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

        for (DetalleTransaccionRequest dt : detalles) {
            DetalleTransaccion detalle = new DetalleTransaccion();
            Producto producto = pDAO.findById(dt.productoId());
            detalle.setCantidad(dt.cantidad());
            detalle.setProducto(producto);
            transaccion.addDetalle(detalle);
        }
    }

    public void actualizarDetalles(List<DetalleTransaccionRequest> detalles, Transaccion transaccion) {
        transaccion.getDetallesTransaccion().clear();
        agregarDetalles(detalles, transaccion);

    }


    // BORRAR UNA TRANSACCION
    public void borrar(TransaccionRequest req) {
        Transaccion transaccion = tDAO.findById(req.transaccionId());
        tDAO.deleteById(transaccion.getId());
    }

    // BUSCAR UNA TRANSACCION
    public TransaccionResponse buscar(Integer id) {
        Transaccion transaccion = tDAO.findById(id);
        return new TransaccionResponse(transaccion.getId(), transaccion.getCliente().getNombre(),transaccion.getTotalBruto(), transaccion.getDescuento(),
                transaccion.getTotalNeto(), transaccion.isConFactura(), transaccion.getFecha());
    }

    public List<TransaccionResponse> listar() {
        List<Transaccion> lista = tDAO.findALL();
        List<TransaccionResponse> listaResp = new ArrayList<>();
        for (Transaccion t : lista) {
            TransaccionResponse e = new TransaccionResponse(t.getId(),t.getCliente().getNombre(), t.getTotalBruto(), t.getDescuento(),
                    t.getTotalNeto(), t.isConFactura(), t.getFecha());
            listaResp.add(e);
        }
        return listaResp;
    }

    

}
