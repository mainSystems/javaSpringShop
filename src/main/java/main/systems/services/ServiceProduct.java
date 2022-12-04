package main.systems.services;

import main.systems.entity.Product;
import main.systems.repositories.ProductRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServiceProduct {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    private ServiceCart cartService;
    private static final Logger logger = LogManager.getLogger(ServiceProduct.class);

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
                if (cartService != null) {
                    Product product = productRepository.getProductsById(productId);
                    cartService.addProducts(product, productCount);
                } else {
                    logger.info("Create cart first");
                }
                break;
            }
            case "del": {
                if (cartService != null) {
                    Product product = productRepository.getProductsById(productId);
                    cartService.delProduct(product, productCount * -1);
                } else {
                    logger.info("Create cart first");
                }
                break;
            }
        }

    }

    public void addProductSubmit(Long productId, int productCount) {
        if (productCount == 0) {
            logger.info("Nothing to add in to cart: " + cartService);
            return;
        }

        String isDel = (productCount < 0) ? "del" : "add";
        switch (isDel) {
            case "add": {
                if (cartService != null) {
                    Product product = productRepository.getProductsById(productId);
                    cartService.addProducts(product, productCount);
                } else {
                    logger.info("Create cart first");
                }
                break;
            }
            case "del": {
                if (cartService != null) {
                    Product product = productRepository.getProductsById(productId);
                    cartService.delProduct(product, productCount * -1);
                } else {
                    logger.info("Create cart first");
                }
                break;
            }
        }
    }
}
