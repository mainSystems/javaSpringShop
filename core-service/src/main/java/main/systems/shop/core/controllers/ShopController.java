package main.systems.shop.core.controllers;


import lombok.extern.slf4j.Slf4j;
import main.systems.shop.api.dto.CountProductsDto;
import main.systems.shop.core.persistence.converters.CustomerConverter;
import main.systems.shop.core.persistence.entity.model.Order;
import main.systems.shop.core.persistence.entity.model.Product;
import main.systems.shop.core.persistence.entity.model.dto.CustomerDto;
import main.systems.shop.core.persistence.entity.model.dto.ProductDto;
import main.systems.shop.core.persistence.services.ServiceOrder;
import main.systems.shop.core.persistence.services.ServiceProduct;
import main.systems.shop.core.persistence.services.ServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/api/v1/shop")
public class ShopController {
    @Autowired
    private ServiceProduct productService;
    @Autowired
    private ServiceOrder orderService;

    @Autowired
    private ServiceUser userService;

    private CustomerConverter customerConverter;

    public ShopController() {
    }


    @GetMapping("/mainPage")
    @ResponseBody
    private List<ProductDto> getProducts() {
        return productService.getProducts().stream().map(product -> new ProductDto(product)).toList();
    }

    @GetMapping("/new_cart")
    @ResponseBody
    private void new_cart() {
        orderService.createNewCart();
        log.info("New cart created: " + orderService);
    }


    @GetMapping("/list_cart")
    @ResponseBody
    private List<Order> list_cart() {
        return orderService.getOrder();
    }

    @GetMapping("/products")
    public String addProductForm(@RequestParam String idProduct, Model model) {
        model.addAttribute("idProduct", idProduct);
        model.addAttribute("countProducts", new CountProductsDto());
        return "productForm";
    }

    @PostMapping("/products")
    public String addProductSubmit(HttpServletRequest request, @ModelAttribute CountProductsDto countProducts, Model
            model) {
        String referer = request.getHeader("Referer");
        Long productId = countProducts.getId();
        int productCount = countProducts.getCount();

        productService.addProductSubmit(productId, productCount);
        return "redirect:" + referer;
    }

    @PostMapping("/productsCount")
    @ResponseBody
    public void changeProductCount(@RequestParam Long productId, @RequestParam int productCount) {
        productService.changeProductCount(productId, productCount);
    }

    @DeleteMapping("/products")
    @ResponseBody
    public void purgeProduct(@RequestParam Long productId) {
        orderService.purgeProductById(productId);
    }

    @GetMapping("/productsCount")
    @ResponseBody
    public long getProductCount(@RequestParam Long productId) {
        Product product = productService.getProductsId(productId);
        return orderService.getOrderProductCount(product);
    }

    @GetMapping("/admin/user_info")
    @ResponseBody
    public List<CustomerDto> getAllCustomers() {
        return userService.getAllCustomer().stream().map(customer -> new CustomerDto(customer)).toList();
    }
}
