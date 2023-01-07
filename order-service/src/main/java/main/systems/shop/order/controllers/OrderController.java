package main.systems.shop.order.controllers;

import lombok.extern.slf4j.Slf4j;
import main.systems.shop.api.entity.dto.OrderDto;
import main.systems.shop.api.entity.dto.ProductDto;
import main.systems.shop.order.integration.ProductServiceIntegration;
import main.systems.shop.order.services.ServiceOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/v1/shop")
public class OrderController {

    @Autowired
    private ServiceOrder orderService;

    @Autowired
    private ProductServiceIntegration productServiceIntegration;

    @GetMapping("/new_order")
    private void new_order() {
        orderService.createNewOrder();
        log.info("New order created: " + orderService);
    }

    @GetMapping("/list_order")
    private List<OrderDto> list_order() {
        return orderService.getOrder().stream().map(order -> new OrderDto(order)).toList();
    }

    @DeleteMapping("/products")
    public void purgeProduct(@RequestParam Long productId) {
        orderService.purgeProductById(productId);
    }

    @GetMapping("/productsCount")
    public long getProductCount(@RequestParam Long productId) {
        ProductDto product = productServiceIntegration.getProductsById(productId).orElseThrow(); //FIX ME Throw readable Exception
        return orderService.getOrderProductCount(product);
    }

    @PostMapping("/productsCount")
    @ResponseBody
    public void changeProductCount(@RequestParam Long productId, @RequestParam int productCount) {
        orderService.addProductSubmit(productId, productCount);
    }
}
