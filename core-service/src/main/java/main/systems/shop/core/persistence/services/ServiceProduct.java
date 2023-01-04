package main.systems.shop.core.persistence.services;

import lombok.extern.slf4j.Slf4j;
import main.systems.shop.core.persistence.entity.model.Product;
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
    @Autowired
    private ServiceOrder orderService;

    public List<Product> getProducts() {
        return productRepository.getProducts();
    }

    public Product getProductsId(Long productId) {
        return productRepository.getProductsById(productId);
    }

    public void changeProductCount(Long productId, int productCount) {
        String isDel = (productCount < 0) ? "del" : "add";
        switch (isDel) {
            case "add": {
                if (orderService != null) {
                    Product product = productRepository.getProductsById(productId);
                    orderService.addProducts(product, productCount);
                } else {
                    log.info("Create cart first");
                }
                break;
            }
            case "del": {
                if (orderService != null) {
                    Product product = productRepository.getProductsById(productId);
                    orderService.delProduct(product, productCount * -1);
                } else {
                    log.info("Create cart first");
                }
                break;
            }
        }

    }

    public void addProductSubmit(Long productId, int productCount) {
        if (productCount == 0) {
            log.info("Nothing to add in to cart: " + orderService);
            return;
        }

        String isDel = (productCount < 0) ? "del" : "add";
        switch (isDel) {
            case "add": {
                if (orderService != null) {
                    Product product = productRepository.getProductsById(productId);
                    orderService.addProducts(product, productCount);
                } else {
                    log.info("Create cart first");
                }
                break;
            }
            case "del": {
                if (orderService != null) {
                    Product product = productRepository.getProductsById(productId);
                    orderService.delProduct(product, productCount * -1);
                } else {
                    log.info("Create cart first");
                }
                break;
            }
        }
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
