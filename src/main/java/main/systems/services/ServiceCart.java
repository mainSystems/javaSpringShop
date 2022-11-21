package main.systems.services;

import main.systems.data.Product;
import main.systems.repositories.CartRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ServiceCart {
    @Autowired
    CartRepository cartRepository;
    private static final Logger logger = LogManager.getLogger(ServiceCart.class);


    public void delProduct(Product product, Integer count) {
        if (count == 0) {
            logger.info("Count of {} to work is 0, nothing to do \n", product.getTitle());
            return;
        }
        if (cartRepository.isContainKey(product)) {
            if (cartRepository.getCountProducts(product).compareTo(count) > 0) {
                cartRepository.updateProduct(product, count);
            } else if (cartRepository.getCountProducts(product).compareTo(count) == 0) {
                logger.info("There are no more products of {} \n", product.getTitle());
                cartRepository.deleteProduct(product);
            } else {
                logger.info("we cant delete more than we have");
                logger.info("products of {} will be removed \n", product.getTitle());
                cartRepository.deleteProduct(product);
            }
        }
    }

    public void addProducts(Product product, Integer count) {
        if (product != null && count > 0) {
            cartRepository.addProduct(product, count);
        }
    }


    public Map<Product, Integer> getCart() {
        return cartRepository.getCart();
    }

    public void createNewCart() {
        cartRepository = new CartRepository();
    }

    public Integer getCartProductCount(Product product) {
        Integer productCount = cartRepository.getCountProducts(product);
        if (productCount != null) {
            return productCount;
        } else {
            return 0;
        }
    }
}
