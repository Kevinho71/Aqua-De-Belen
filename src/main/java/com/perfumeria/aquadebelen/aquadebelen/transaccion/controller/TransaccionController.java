package com.perfumeria.aquadebelen.aquadebelen.transaccion.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perfumeria.aquadebelen.aquadebelen.transaccion.DTO.TransaccionRequest;
import com.perfumeria.aquadebelen.aquadebelen.transaccion.DTO.TransaccionResponse;
import com.perfumeria.aquadebelen.aquadebelen.transaccion.presenter.TransaccionPresenter;
import com.perfumeria.aquadebelen.aquadebelen.transaccion.service.TransaccionService;
import com.perfumeria.aquadebelen.aquadebelen.transaccion.viewmodel.ListTransaccionViewModel;
import com.perfumeria.aquadebelen.aquadebelen.transaccion.viewmodel.TransaccionViewModel;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Validated
@RestController
@RequestMapping("/api/v1")
public class TransaccionController {

    private final TransaccionService transaccionService;
    private final TransaccionPresenter transaccionPresenter;

    public TransaccionController(TransaccionService transaccionService, TransaccionPresenter transaccionPresenter) {
        this.transaccionService = transaccionService;
        this.transaccionPresenter = transaccionPresenter;
    }

    @PostMapping("/transacciones")
    public ResponseEntity<TransaccionViewModel> registrar(@Valid @RequestBody TransaccionRequest req) {
        TransaccionResponse resp = transaccionService.store(null, req);
        TransaccionViewModel tvm = transaccionPresenter.present(resp);
        return ResponseEntity.ok(tvm);  
    }

    @PutMapping("/transacciones/{id}")
    public ResponseEntity<TransaccionViewModel> editar(@Valid @RequestBody TransaccionRequest req, @PathVariable("id") @Min(1) Integer id) {
        TransaccionResponse resp = transaccionService.store(id, req);
        TransaccionViewModel tvm = transaccionPresenter.present(resp);

        return ResponseEntity.ok(tvm);
    }

    @DeleteMapping("/transacciones/{id}")
    public void borrar(@PathVariable("id") @Min(1) Integer id) {
        transaccionService.borrar(id);
    }

    @GetMapping("/transacciones")
    public ResponseEntity<List<ListTransaccionViewModel>> listar() {
        List<TransaccionResponse> resp = transaccionService.listar();
        List<ListTransaccionViewModel> ltvm = transaccionPresenter.presentList(resp);
        return ResponseEntity.ok(ltvm);
    }

    @GetMapping("/transacciones/{id}")
    public ResponseEntity<TransaccionViewModel> buscar(@PathVariable("id") @Min(1) Integer id) {
        TransaccionResponse resp = transaccionService.buscar(id);
       TransaccionViewModel tvm = transaccionPresenter.present(resp);
        return ResponseEntity.ok(tvm);
    }


}
