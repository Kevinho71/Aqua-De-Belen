package com.perfumeria.aquadebelen.aquadebelen.transaccion.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.perfumeria.aquadebelen.aquadebelen.clientes.model.Cliente;
import com.perfumeria.aquadebelen.aquadebelen.clientes.repository.ClienteDAO;
import com.perfumeria.aquadebelen.aquadebelen.productos.model.Producto;
import com.perfumeria.aquadebelen.aquadebelen.productos.repository.ProductoDAO;
import com.perfumeria.aquadebelen.aquadebelen.transaccion.DTO.DetalleTransaccionRequest;
import com.perfumeria.aquadebelen.aquadebelen.transaccion.DTO.TransaccionRequest;
import com.perfumeria.aquadebelen.aquadebelen.transaccion.DTO.TransaccionResponse;
import com.perfumeria.aquadebelen.aquadebelen.transaccion.model.DetalleTransaccion;
import com.perfumeria.aquadebelen.aquadebelen.transaccion.model.Factura;
import com.perfumeria.aquadebelen.aquadebelen.transaccion.model.MetodoDePago;
import com.perfumeria.aquadebelen.aquadebelen.transaccion.model.Transaccion;
import com.perfumeria.aquadebelen.aquadebelen.transaccion.repository.MetodoDePagoDAO;
import com.perfumeria.aquadebelen.aquadebelen.transaccion.repository.TransaccionDAO;

import jakarta.transaction.Transactional;

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

    @Transactional
    public TransaccionResponse registrar(TransaccionRequest req) {

        // CREAMOS UNA NUEVA TRANSACCION A LA QUE IREMOS ASIGNANDO SUS RESPECTIVOS
        // VALORES SACADOS DEL DTO
        Transaccion transaccion = new Transaccion();

        // ASIGNAMOS A VARIABLES TEMPORALES LOS VALORES DEL DTO DE TRANSACCION
        MetodoDePago metodoDePago = mpDAO.findById(req.metodoDePagoId());
        Cliente cliente = cDAO.findById(req.clienteId());
        double descuento = req.descuento();
        LocalDateTime fecha = LocalDateTime.now();
        double totalBruto = calcularTotalBruto(req.detalles());
        double totalNeto = totalBruto - descuento;
        boolean conFactura = req.conFactura();
        Integer id = tDAO.nextId();

        agregarDetalles(req.detalles(), transaccion);

       /* // FACTURA OPCIONAL
        if (req.conFactura()) {
            Factura factura = new Factura();
            factura.setFechaEmision(LocalDateTime.now());
            factura.setRazonSocial(req.factura().razonSocial());
            factura.setNit(cliente.getNitCi());
            factura.setTransaccion(transaccion);
            transaccion.setFactura(factura);
        }*/

        guardarTransaccion(transaccion, cliente, metodoDePago, descuento, fecha, totalNeto, totalBruto, conFactura, id);
        return new TransaccionResponse(transaccion.getId(), transaccion.getTotalBruto(), transaccion.getDescuento(),
                transaccion.getTotalNeto(), transaccion.isConFactura());

    }

    public TransaccionResponse editar(TransaccionRequest req) {
        Transaccion transaccion = tDAO.findById(req.transaccionId());
        transaccion.setCliente(cDAO.findById(req.clienteId()));
        transaccion.setMetodoDePago(mpDAO.findById(req.metodoDePagoId()));
        transaccion.setDescuento(req.descuento());
        transaccion.setTotalBruto(calcularTotalBruto(req.detalles()));
        transaccion.setTotalNeto(transaccion.getTotalBruto() - transaccion.getDescuento());
        transaccion.setConFactura(req.conFactura());
        actualizarDetalles(req.detalles(), transaccion);

        tDAO.edit(transaccion);
        return new TransaccionResponse(transaccion.getId(), transaccion.getTotalBruto(), transaccion.getDescuento(),
                transaccion.getTotalNeto(), transaccion.isConFactura());
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

    public void guardarTransaccion(Transaccion transaccion, Cliente cliente, MetodoDePago metodoDePago,
            double descuento, LocalDateTime fecha, double totalNeto, double totalBruto, boolean conFactura, Integer id) {
        transaccion.setCliente(cliente);
        transaccion.setMetodoDePago(metodoDePago);
        transaccion.setDescuento(descuento);
        transaccion.setFecha(fecha);
        transaccion.setTotalNeto(totalNeto);
        transaccion.setTotalBruto(totalBruto);
        transaccion.setConFactura(conFactura);
        transaccion.setId(id);
        tDAO.register(transaccion);
    }

    // BORRAR UNA TRANSACCION
    public void borrar(TransaccionRequest req) {
        Transaccion transaccion = tDAO.findById(req.transaccionId());
        tDAO.deleteById(transaccion.getId());
    }

    // BUSCAR UNA TRANSACCION
    public TransaccionResponse buscar(Integer id) {
        Transaccion transaccion = tDAO.findById(id);
        return new TransaccionResponse(transaccion.getId(), transaccion.getTotalBruto(), transaccion.getDescuento(),
                transaccion.getTotalNeto(), transaccion.isConFactura());
    }

    public List<TransaccionResponse> listar() {
        List<Transaccion> lista = tDAO.findALL();
        List<TransaccionResponse> listaResp = new ArrayList<>();
        for(Transaccion t:lista){
            TransaccionResponse e = new TransaccionResponse(t.getId(), t.getTotalBruto(), t.getDescuento(), t.getTotalNeto(),  t.isConFactura());
            listaResp.add(e);
        }
        return listaResp;
    }

}
