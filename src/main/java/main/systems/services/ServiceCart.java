package main.systems.services;

import main.systems.entity.Order;
import main.systems.entity.Product;
import main.systems.repositories.CartRepository;
import main.systems.repositories.ProductRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ServiceCart {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CartRepository cartRepository;
    private static final Logger logger = LogManager.getLogger(ServiceCart.class);


    public void delProduct(Product product, Integer count) {
        if (count == 0) {
            logger.info("Count of {} to work is 0, nothing to do \n", product.getTitle());
            return;
        }
        if (cartRepository.getCountProducts(product) > 0) {
            cartRepository.deleteProduct(product, count);
        } else if (cartRepository.getCountProducts(product) == 0) {
            logger.info("There are no more products of {} \n", product.getTitle());
            cartRepository.purgeProduct(product);
        } else {
            logger.info("we cant delete more than we have");
            logger.info("products of {} will be removed \n", product.getTitle());
            cartRepository.purgeProduct(product);
        }
    }

    public void addProducts(Product product, Integer count) {
        if (product != null && count > 0) {
            cartRepository.addProduct(product, count);
        }
    }


    public List<Order> getCart() {
        return cartRepository.getCart();
    }

    public void createNewCart() {
        cartRepository.purgeAllProduct();
    }

    public void purgeProductById(Long productId) {
        Product product = productRepository.getProductsById(productId);
        cartRepository.purgeProduct(product);
    }

    public long getCartProductCount(Product product) {
        return cartRepository.getCountProducts(product);
    }
}
