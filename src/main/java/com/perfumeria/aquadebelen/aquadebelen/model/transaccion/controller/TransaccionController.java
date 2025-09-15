package com.perfumeria.aquadebelen.aquadebelen.model.transaccion.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perfumeria.aquadebelen.aquadebelen.model.transaccion.DTO.TransaccionRequest;
import com.perfumeria.aquadebelen.aquadebelen.model.transaccion.DTO.TransaccionResponse;
import com.perfumeria.aquadebelen.aquadebelen.model.transaccion.service.TransaccionService;


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

}
