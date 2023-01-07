package main.systems.shop.order.services;

import lombok.extern.slf4j.Slf4j;
import main.systems.shop.api.entity.dto.CustomerDto;
import main.systems.shop.api.entity.dto.ProductDto;
import main.systems.shop.api.entity.model.Customer;
import main.systems.shop.api.entity.model.Order;
import main.systems.shop.order.integration.CustomerServiceIntegration;
import main.systems.shop.order.integration.ProductServiceIntegration;
import main.systems.shop.order.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class ServiceOrder {
    @Autowired
    ProductServiceIntegration productServiceIntegration;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    CustomerServiceIntegration customerServiceIntegration;



    public void delProduct(ProductDto product, Integer count) {
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

    public void addProducts(ProductDto product, Integer count) {
        if (product != null && count > 0) {
            CustomerDto customer = customerServiceIntegration.getCustomerById(1L).orElseThrow(); //FIX ME Throw readable Exception
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
                ProductDto product = productServiceIntegration.getProductsById(productId).orElseThrow(); //FIX ME Throw readable Exception
                addProducts(product, productCount);
                break;
            }
            case "del": {
                ProductDto product = productServiceIntegration.getProductsById(productId).orElseThrow(); //FIX ME Throw readable Exception
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
        ProductDto product = productServiceIntegration.getProductsById(productId).orElseThrow(); //FIX ME Throw readable Exception
        orderRepository.purgeProduct(product);
    }

    public long getOrderProductCount(ProductDto product) {
        return orderRepository.getCountProducts(product);
    }
}
