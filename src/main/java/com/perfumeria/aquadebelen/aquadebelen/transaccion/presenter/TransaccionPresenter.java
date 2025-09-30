package com.perfumeria.aquadebelen.aquadebelen.transaccion.presenter;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.perfumeria.aquadebelen.aquadebelen.transaccion.DTO.TransaccionResponse;

@Component
public class TransaccionPresenter {
    String transaccionId;
    String cliente;
    String totalBruto;
    String descuento;
    String totalNeto;
    String conFactura;
    String fecha;

    public TransaccionPresenter format(TransaccionResponse resp) {
        TransaccionPresenter pres = new TransaccionPresenter();
        pres.setTransaccionId(String.valueOf(resp.transaccionId()));
        pres.setCliente(resp.cliente().toString());
        pres.setTotalBruto(String.valueOf(resp.totalBruto()) + " Bs");
        pres.setTotalNeto(String.valueOf(resp.totalNeto()) + " Bs");
        pres.setDescuento(String.valueOf(resp.descuento()) + " Bs");

        if (resp.conFactura() == true) {
            pres.setConFactura("Con factura");
        } else {
            pres.setConFactura("Sin factura");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        pres.setFecha(resp.fecha().format(formatter)); 

        return pres;
    }

    public List<TransaccionPresenter> formatList(List<TransaccionResponse> listaResp){
        List<TransaccionPresenter> listPres = new ArrayList<>();
        for(TransaccionResponse resp : listaResp){
            TransaccionPresenter pres = format(resp);
            listPres.add(pres);
        }
        return listPres;
    }

    public TransaccionPresenter(){

    }

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
