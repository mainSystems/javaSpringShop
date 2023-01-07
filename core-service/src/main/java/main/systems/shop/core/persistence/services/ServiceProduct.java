package main.systems.shop.core.persistence.services;

import lombok.extern.slf4j.Slf4j;
import main.systems.shop.api.entity.model.Product;
import main.systems.shop.core.persistence.repositories.ProductRepository;
import main.systems.shop.core.soap.products.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@Slf4j
public class ServiceProduct {
    @Autowired
    ProductRepository productRepository;

    public List<Product> getProducts() {
        return productRepository.getProducts();
    }

    public Product getProductsById(Long productId) {
        return productRepository.getProductsById(productId);
    }

    public static final Function<Product, Products> functionEntityToSoap = se -> {
        Products s = new Products();
        s.setId(se.getId());
        s.setTitle(se.getTitle());
        s.setCost(se.getCost());
        return s;
    };

    public List<Products> getAllProductsSoap() {
        return productRepository.getProducts().stream().map(functionEntityToSoap).collect(Collectors.toList());
    }
}
