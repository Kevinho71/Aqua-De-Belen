package com.perfumeria.aquadebelen.aquadebelen.api.stats;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perfumeria.aquadebelen.aquadebelen.api.stats.dto.CategoryCardView;
import com.perfumeria.aquadebelen.aquadebelen.api.stats.dto.DashboardStatsResponse;

@RestController
@RequestMapping("/api/stats")
public class StatsController {

    private final StatsService stats;
    public StatsController(StatsService stats){ this.stats = stats; }

    @GetMapping("/dashboard")
    public DashboardStatsResponse dashboard(){ return stats.dashboard(); }

    @GetMapping("/categories")
    public List<CategoryCardView> categories(){ return stats.categories(); }
}
