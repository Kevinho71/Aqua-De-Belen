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
import com.perfumeria.aquadebelen.aquadebelen.transaccion.service.TransaccionService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/api/transacciones")
public class TransaccionController {

    private final TransaccionService transaccionService;

    public TransaccionController(TransaccionService transaccionService){
        this.transaccionService=transaccionService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<TransaccionResponse> registrar(@RequestBody TransaccionRequest req){
        TransaccionResponse resp = transaccionService.registrar(req);
        return ResponseEntity.ok(resp);
    }

    @PutMapping("/editar")
    public ResponseEntity<TransaccionResponse> editar(@RequestBody TransaccionRequest req) {
        TransaccionResponse resp = transaccionService.editar(req);
        return ResponseEntity.ok(resp);
    }

    @DeleteMapping("/borrar")
    public void borrar(@RequestBody TransaccionRequest req){
        transaccionService.borrar(req);
    }
    
    @GetMapping("/listar")
    public ResponseEntity<List<TransaccionResponse>> listar(){
        List<TransaccionResponse> resp = transaccionService.listar();
        return ResponseEntity.ok(resp);
    }
    
    @GetMapping("/buscar/{id}")
    public ResponseEntity<TransaccionResponse> getMethodName(@PathVariable("id") Integer id ){
        TransaccionResponse resp = transaccionService.buscar(id);
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/{id}/detalles")
    public ResponseEntity<DetalleTransaccionResponse> obtenerDetalles(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(null);
    }
    
    
}
