package com.perfumeria.aquadebelen.aquadebelen.model.transaccion.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.perfumeria.aquadebelen.aquadebelen.model.clientes.domain.Cliente;
import com.perfumeria.aquadebelen.aquadebelen.model.clientes.repository.ClienteDAO;
import com.perfumeria.aquadebelen.aquadebelen.model.productos.domain.Producto;
import com.perfumeria.aquadebelen.aquadebelen.model.productos.repository.ProductoDAO;
import com.perfumeria.aquadebelen.aquadebelen.model.transaccion.DTO.DetalleTransaccionRequest;
import com.perfumeria.aquadebelen.aquadebelen.model.transaccion.DTO.TransaccionRequest;
import com.perfumeria.aquadebelen.aquadebelen.model.transaccion.DTO.TransaccionResponse;
import com.perfumeria.aquadebelen.aquadebelen.model.transaccion.domain.DetalleTransaccion;
import com.perfumeria.aquadebelen.aquadebelen.model.transaccion.domain.Factura;
import com.perfumeria.aquadebelen.aquadebelen.model.transaccion.domain.MetodoDePago;
import com.perfumeria.aquadebelen.aquadebelen.model.transaccion.domain.Transaccion;
import com.perfumeria.aquadebelen.aquadebelen.model.transaccion.repository.MetodoDePagoDAO;
import com.perfumeria.aquadebelen.aquadebelen.model.transaccion.repository.TransaccionDAO;

import jakarta.transaction.Transactional;

@Service
public class TransaccionService {
    private final TransaccionDAO tDAO;
    private final MetodoDePagoDAO mpDAO;
    private final ClienteDAO cDAO;
    private final ProductoDAO pDAO;

    public TransaccionService(TransaccionDAO tDAO, MetodoDePagoDAO mpDAO, ClienteDAO cDAO
                                , ProductoDAO pDAO){
        this.tDAO=tDAO;
        this.mpDAO=mpDAO;
        this.cDAO =cDAO;
        this.pDAO=pDAO;
    }

    @Transactional
    public TransaccionResponse registrar(TransaccionRequest req) {

        //CREAMOS UNA NUEVA TRANSACCION A LA QUE IREMOS ASIGNANDO SUS RESPECTIVOS VALORES SACADOS DEL DTO
        Transaccion transaccion = new Transaccion();

        //ASIGNAMOS A VARIABLES TEMPORALES LOS VALORES DEL DTO DE TRANSACCION
        MetodoDePago metodoDePago = mpDAO.findById(req.metodoDePagoId());
        Cliente cliente = cDAO.findById(req.clienteId());
        double descuento = req.descuento();
        LocalDateTime fecha = LocalDateTime.now();
        double totalBruto = 0;

        //SETTEAMOS ALGUNOS CAMPOS PARA LA TRANSACCION
        transaccion.setCliente(cliente);
        transaccion.setMetodoDePago(metodoDePago);
        transaccion.setDescuento(descuento);
        transaccion.setFecha(fecha);

        //Aqui hacemos un for each para cada detalle de transaccion dentro del atributo detalles que es una List<>
        for(DetalleTransaccionRequest dt : req.detalles()){

            //AQUI CREAMOS UN NUEVO PRODUCTO CON EL FIN DE HALLAR SU PRECIO Y VAMOS CALCULANDO EL TOTAL BRUTO
            Producto producto = pDAO.findById(dt.productoId());
            // Evitar NullPointerException si el precio es null
            double precio = (producto.getPrecio() != null) ? producto.getPrecio() : 0.0;
            double subtotal = precio * dt.cantidad();
            totalBruto += subtotal;
            
            //AQUI ASIGNAMOS LOS DETALLES DEL DTO A NUEVAS ENTIDADES DETALLES Y LUEGE SETTEAMOS UNO POR UNO A LA TRANSACCION
            DetalleTransaccion detalle = new DetalleTransaccion();
            detalle.setCantidad(dt.cantidad());
            detalle.setProducto(producto);
            transaccion.addDetalle(detalle);
            
        }
        //ASIGNAMOS EL TOTAL NETO DE ACUERDO AL TOTAL BRUTO CALCULADO MENOS EL DESCUENTO
        double totalNeto=totalBruto-descuento;
        transaccion.setMonto(totalNeto);

        //FACTURA OPCIONAL

        if (req.conFactura()){
            Factura factura = new Factura();
            factura.setFechaEmision(LocalDateTime.now());
            factura.setRazonSocial(req.factura().razonSocial());
            factura.setNit(cliente.getNitCi());
            factura.setTransaccion(transaccion);
            transaccion.setFactura(factura);
        } 

        //GUARDAR LA TRANSACCION
        tDAO.register(transaccion);
        return new TransaccionResponse(transaccion.getId(), totalBruto, descuento, totalNeto, req.conFactura());
       
    }

}
