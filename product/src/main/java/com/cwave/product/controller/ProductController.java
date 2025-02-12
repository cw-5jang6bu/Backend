package com.cwave.product.controller;

import com.cwave.product.domain.Product;
import com.cwave.product.domain.entity.ProductEntity;
import com.cwave.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;

    @GetMapping
    public List<ProductEntity> getProducts() {
        return productRepository.findAll();
    }
}
