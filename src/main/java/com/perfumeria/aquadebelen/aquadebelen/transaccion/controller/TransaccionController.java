package com.perfumeria.aquadebelen.aquadebelen.transaccion.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perfumeria.aquadebelen.aquadebelen.transaccion.DTO.DetalleTransaccionResponse;
import com.perfumeria.aquadebelen.aquadebelen.transaccion.DTO.TransaccionRequest;
import com.perfumeria.aquadebelen.aquadebelen.transaccion.DTO.TransaccionResponse;
import com.perfumeria.aquadebelen.aquadebelen.transaccion.presenter.TransaccionPresenter;
import com.perfumeria.aquadebelen.aquadebelen.transaccion.service.TransaccionService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/api/transacciones")
public class TransaccionController {

    private final TransaccionService transaccionService;
    private final TransaccionPresenter transaccionPresenter;

    public TransaccionController(TransaccionService transaccionService, TransaccionPresenter transaccionPresenter){
        this.transaccionService=transaccionService;
        this.transaccionPresenter= transaccionPresenter;
    }

    @PostMapping("/registrar")
    public ResponseEntity<TransaccionPresenter> registrar(@RequestBody TransaccionRequest req){
        TransaccionResponse resp = transaccionService.store(req);
        TransaccionPresenter pres = transaccionPresenter.format(resp);
        return ResponseEntity.ok(pres);
    }

    @PutMapping("/editar")
    public ResponseEntity<TransaccionPresenter> editar(@RequestBody TransaccionRequest req) {
        TransaccionResponse resp = transaccionService.store(req);
        TransaccionPresenter pres = transaccionPresenter.format(resp);
        return ResponseEntity.ok(pres);
    }

    @DeleteMapping("/borrar")
    public void borrar(@RequestBody TransaccionRequest req){
        transaccionService.borrar(req);
    }
    
    @GetMapping("/listar")
    public ResponseEntity<List<TransaccionPresenter>> listar(){
        List<TransaccionResponse> resp = transaccionService.listar();
        List<TransaccionPresenter> pres = transaccionPresenter.formatList(resp);
        return ResponseEntity.ok(pres);
    }
    
    @GetMapping("/buscar/{id}")
    public ResponseEntity<TransaccionPresenter> getMethodName(@PathVariable("id") Integer id ){
        TransaccionResponse resp = transaccionService.buscar(id);
        TransaccionPresenter pres = transaccionPresenter.format(resp);
        return ResponseEntity.ok(pres);
    }

    @GetMapping("/{id}/detalles")
    public ResponseEntity<DetalleTransaccionResponse> obtenerDetalles(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(null);
    }
    
    
}
