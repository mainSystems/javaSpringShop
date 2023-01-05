package main.systems.shop.core.controllers;


import lombok.extern.slf4j.Slf4j;
import main.systems.shop.core.persistence.entity.dto.ProductDto;
import main.systems.shop.core.persistence.services.ServiceProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/v1/shop")
public class ProductControllerRest {
    @Autowired
    private ServiceProduct productService;


    @GetMapping("/mainPage")
    @ResponseBody
    private List<ProductDto> getProducts() {
        return productService.getProducts().stream().map(product -> new ProductDto(product)).toList();
    }

    @PostMapping("/productsCount")
    @ResponseBody
    public void changeProductCount(@RequestParam Long productId, @RequestParam int productCount) {
        productService.changeProductCount(productId, productCount);
    }

    @GetMapping("/product/{productId}")
    @ResponseBody
    public ProductDto getProductById(@PathVariable(name = "productId") Long productId) {
        return new ProductDto(productService.getProductsById(productId));
    }
}
