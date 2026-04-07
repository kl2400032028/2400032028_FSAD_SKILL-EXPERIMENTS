package com.skill.controller;

import com.skill.model.Product;
import com.skill.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    // 4a. /products/category/{category}
    @GetMapping("/category/{category}")
    public List<Product> getProductsByCategory(@PathVariable String category) {
        return productRepository.findByCategory(category);
    }

    // 4b. /products/filter?min=&max=
    @GetMapping("/filter")
    public List<Product> getProductsByPriceRange(@RequestParam double min, @RequestParam double max) {
        return productRepository.findByPriceBetween(min, max);
    }

    // 4c. /products/sorted
    @GetMapping("/sorted")
    public List<Product> getSortedProducts() {
        return productRepository.findAllSortedByPrice();
    }

    // 4d. /products/expensive/{price}
    @GetMapping("/expensive/{price}")
    public List<Product> getExpensiveProducts(@PathVariable double price) {
        return productRepository.findProductsExpensiveThan(price);
    }
}
