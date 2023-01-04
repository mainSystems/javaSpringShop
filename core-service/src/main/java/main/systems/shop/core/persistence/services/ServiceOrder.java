package main.systems.shop.core.persistence.services;

import lombok.extern.slf4j.Slf4j;
import main.systems.shop.core.persistence.entity.model.Order;
import main.systems.shop.core.persistence.entity.model.Product;
import main.systems.shop.core.persistence.repositories.OrderRepository;
import main.systems.shop.core.persistence.repositories.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class ServiceOrder {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderRepository orderRepository;


    public void delProduct(Product product, Integer count) {
        if (count == 0) {
            log.info("Count of {} to work is 0, nothing to do \n", product.getTitle());
            return;
        }
        if (orderRepository.getCountProducts(product) > 0) {
            orderRepository.deleteProduct(product, count);
        } else if (orderRepository.getCountProducts(product) == 0) {
            log.info("There are no more products of {} \n", product.getTitle());
            orderRepository.purgeProduct(product);
        } else {
            log.info("we cant delete more than we have");
            log.info("products of {} will be removed \n", product.getTitle());
            orderRepository.purgeProduct(product);
        }
    }

    public void addProducts(Product product, Integer count) {
        if (product != null && count > 0) {
            orderRepository.addProduct(product, count);
        }
    }


    public List<Order> getOrder() {
        return orderRepository.getOrder();
    }

    public void createNewOrder() {
        orderRepository.purgeAllProduct();
    }

    public void purgeProductById(Long productId) {
        Product product = productRepository.getProductsById(productId);
        orderRepository.purgeProduct(product);
    }

    public long getOrderProductCount(Product product) {
        return orderRepository.getCountProducts(product);
    }
}
