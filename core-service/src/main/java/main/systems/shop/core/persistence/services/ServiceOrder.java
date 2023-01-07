package main.systems.shop.core.persistence.services;

import lombok.extern.slf4j.Slf4j;
import main.systems.shop.api.entity.model.Customer;
import main.systems.shop.api.entity.model.Order;
import main.systems.shop.api.entity.model.Product;
import main.systems.shop.core.persistence.repositories.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class ServiceOrder {
    @Autowired
    ServiceProduct serviceProduct;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ServiceCustomer serviceCustomer;



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
            Customer customer = serviceCustomer.getCustomerById(1L); //FIX ME
            orderRepository.addProduct(product, count, customer);
        }
    }

    public void addProductSubmit(Long productId, int productCount) {
        if (productCount == 0) {
            return;
        }

        String isDel = (productCount < 0) ? "del" : "add";
        switch (isDel) {
            case "add": {
                Product product = serviceProduct.getProductsById(productId);
                addProducts(product, productCount);
                break;
            }
            case "del": {
                Product product = serviceProduct.getProductsById(productId);
                delProduct(product, productCount * -1);
                break;
            }
        }
    }

    public List<Order> getOrder() {
        return orderRepository.getOrder();
    }

    public void createNewOrder() {
        orderRepository.purgeAllProduct();
    }

    public void purgeProductById(Long productId) {
        Product product = serviceProduct.getProductsById(productId);
        orderRepository.purgeProduct(product);
    }

    public long getOrderProductCount(Product product) {
        return orderRepository.getCountProducts(product);
    }
}
