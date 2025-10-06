package com.perfumeria.aquadebelen.aquadebelen.transaccion.viewmodel;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;


@Component
public class TransaccionViewModel {
    String transaccionId;
    String cliente;
    String totalBruto;
    String descuentoTotal;
    String totalNeto;
    String conFactura;
    String fecha;
    List<DetalleTransaccionViewModel> detalles;

    /*public TransaccionViewModel(TransaccionPresenter pres){
        this.transaccionId=pres.getTransaccionId();
        this.cliente=pres.getCliente();
        this.totalBruto=pres.getTotalBruto();
        this.descuentoTotal=pres.getdescuentoTotal();
        this.totalNeto=pres.getTotalNeto();
        this.conFactura=pres.getConFactura();
        this.fecha=pres.getFecha();
    }*/

    public TransaccionViewModel(){};

    /*public List<TransaccionViewModel> list (List<TransaccionPresenter> listPres){
        List<TransaccionViewModel> lista = new ArrayList<>();
        for (TransaccionPresenter tp : listPres){
            TransaccionViewModel tvm = new TransaccionViewModel(tp);
            lista.add(tvm);
        }

        return lista;
    }*/

    public void add(DetalleTransaccionViewModel d){
        if(this.detalles == null){
            List<DetalleTransaccionViewModel> lista = new ArrayList<>();
            this.setDetalles(lista);
        }
        detalles.add(d);
    }

    public String getTransaccionId() {
        return transaccionId;
    }

    public String getCliente() {
        return cliente;
    }

    public String getTotalBruto() {
        return totalBruto;
    }

    public String getdescuentoTotal() {
        return descuentoTotal;
    }

    public String getTotalNeto() {
        return totalNeto;
    }

    public String getConFactura() {
        return conFactura;
    }

    public String getFecha() {
        return fecha;
    }

    public List<DetalleTransaccionViewModel> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleTransaccionViewModel> detalles) {
        this.detalles = detalles;
    }

    public void setTransaccionId(String transaccionId) {
        this.transaccionId = transaccionId;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public void setTotalBruto(String totalBruto) {
        this.totalBruto = totalBruto;
    }

    public void setdescuentoTotal(String descuentoTotal) {
        this.descuentoTotal = descuentoTotal;
    }

    public void setTotalNeto(String totalNeto) {
        this.totalNeto = totalNeto;
    }

    public void setConFactura(String conFactura) {
        this.conFactura = conFactura;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    
}
