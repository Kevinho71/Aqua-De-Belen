package com.perfumeria.aquadebelen.aquadebelen.api.dashboard;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.perfumeria.aquadebelen.aquadebelen.api.dashboard.dto.DashboardResumenDTO;
import com.perfumeria.aquadebelen.aquadebelen.api.dashboard.dto.VentaRecienteDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService service;

    @GetMapping("/resumen")
    public DashboardResumenDTO resumen() {
        return service.resumen();
    }

    @GetMapping("/recientes")
    public List<VentaRecienteDTO> recientes(@RequestParam(defaultValue = "4") int limit) {
        return service.ventasRecientes(limit);
    }
}
