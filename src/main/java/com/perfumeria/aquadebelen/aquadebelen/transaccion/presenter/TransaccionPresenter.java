package com.perfumeria.aquadebelen.aquadebelen.transaccion.presenter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.perfumeria.aquadebelen.aquadebelen.transaccion.DTO.DetalleTransaccionResponse;
import com.perfumeria.aquadebelen.aquadebelen.transaccion.DTO.TransaccionResponse;
import com.perfumeria.aquadebelen.aquadebelen.transaccion.viewmodel.DetalleTransaccionViewModel;
import com.perfumeria.aquadebelen.aquadebelen.transaccion.viewmodel.ListTransaccionViewModel;
import com.perfumeria.aquadebelen.aquadebelen.transaccion.viewmodel.TransaccionViewModel;

@Component
public class TransaccionPresenter {

    String transaccionId;
    String cliente;
    String totalBruto;
    String descuento;
    String totalNeto;
    String conFactura;
    String fecha;

    public TransaccionViewModel present(TransaccionResponse resp) {
        TransaccionViewModel tvm = new TransaccionViewModel();
        tvm.setTransaccionId(String.valueOf(resp.transaccionId()));
        tvm.setCliente(resp.cliente().toString());
        tvm.setTotalBruto(String.valueOf(resp.totalBruto()) + " Bs");
        tvm.setTotalNeto(String.valueOf(resp.totalNeto()) + " Bs");
        tvm.setdescuentoTotal(String.valueOf(resp.descuentoTotal()) + " Bs");

        tvm.setConFactura(formatFactura(resp.conFactura()));
        tvm.setFecha(formatFecha(resp.fecha()));
        tvm.setDetalles(presentDetalles(resp.detalles()));

        return tvm;
    }

    public List<DetalleTransaccionViewModel> presentDetalles(List<DetalleTransaccionResponse> listResp) {
        List<DetalleTransaccionViewModel> dtvm = new ArrayList<>();
        for (DetalleTransaccionResponse dtr : listResp) {
            DetalleTransaccionViewModel d = new DetalleTransaccionViewModel();
            d.setIdDetalle(String.valueOf(dtr.detalleId()));
            d.setProducto(dtr.producto());
            d.setCostoUnitario(String.valueOf(dtr.costoUnitario()) + " Bs");
            d.setCantidad(String.valueOf(dtr.cantidad()) + " u");
            d.setDescuento(String.valueOf(dtr.descuento()) + " Bs");
            d.setSubtotal(String.valueOf(dtr.subtotal()) + " Bs");

            dtvm.add(d);
        }

        return dtvm;
    }

    public List<ListTransaccionViewModel> presentList(List<TransaccionResponse> listaResp) {
        List<ListTransaccionViewModel> lista = new ArrayList<>();
        for (TransaccionResponse resp : listaResp) {
            ListTransaccionViewModel listPres = new ListTransaccionViewModel();
            listPres.setTransaccionId(String.valueOf(resp.transaccionId()));
            listPres.setCliente(resp.cliente());
            listPres.setTotalNeto(String.valueOf(resp.totalNeto()) + " Bs");
            listPres.setConFactura(formatFactura(resp.conFactura()));
            listPres.setFecha(formatFecha(resp.fecha()));
            lista.add(listPres);

        }
        return lista;
    }

    public String formatFecha(LocalDateTime fecha) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedFecha = fecha.format(formatter);

        return formattedFecha;
    }

    public String formatFactura(Boolean factura) {
        String formattedFactura;
        if (factura == true) {
            formattedFactura = "Con factura";
        } else {
            formattedFactura = "Sin factura";
        }

        return formattedFactura;
    }

    // ====================================================================================================================
    // CONSTRUCTORES, GETTERS Y SETTERS

    public TransaccionPresenter(String transaccionId, String cliente, String totalBruto, String descuento,
            String totalNeto, String conFactura, String fecha) {
        this.transaccionId = transaccionId;
        this.cliente = cliente;
        this.totalBruto = totalBruto;
        this.descuento = descuento;
        this.totalNeto = totalNeto;
        this.conFactura = conFactura;
        this.fecha = fecha;
    }

    public TransaccionPresenter() {

    }

    public String getTransaccionId() {
        return transaccionId;
    }

    public void setTransaccionId(String transaccionId) {
        this.transaccionId = transaccionId;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getTotalBruto() {
        return totalBruto;
    }

    public void setTotalBruto(String totalBruto) {
        this.totalBruto = totalBruto;
    }

    public String getDescuento() {
        return descuento;
    }

    public void setDescuento(String descuento) {
        this.descuento = descuento;
    }

    public String getTotalNeto() {
        return totalNeto;
    }

    public void setTotalNeto(String totalNeto) {
        this.totalNeto = totalNeto;
    }

    public String getConFactura() {
        return conFactura;
    }

    public void setConFactura(String conFactura) {
        this.conFactura = conFactura;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

}
