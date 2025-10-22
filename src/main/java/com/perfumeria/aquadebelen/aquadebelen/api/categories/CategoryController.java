package com.perfumeria.aquadebelen.aquadebelen.api.categories;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perfumeria.aquadebelen.aquadebelen.api.categories.dto.CategoryCardDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;

    @GetMapping("/cards")
    public List<CategoryCardDTO> cards() {
        return service.cards();
    }
}
