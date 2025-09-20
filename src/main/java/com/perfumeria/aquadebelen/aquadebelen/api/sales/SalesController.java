package com.perfumeria.aquadebelen.aquadebelen.api.sales;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perfumeria.aquadebelen.aquadebelen.api.sales.dto.SaleView;

@RestController
@RequestMapping("/api/ventas")
public class SalesController {

    private final SalesQueryService service;
    public SalesController(SalesQueryService service){ this.service = service; }

    @GetMapping
    public List<SaleView> list(){ return service.listAll(); }
}
