package main.systems.shop.core.controllers;


import main.systems.shop.core.persistence.converters.CustomerConverter;
import main.systems.shop.core.persistence.entity.dto.CountProductsDto;
import main.systems.shop.core.persistence.entity.dto.CustomerDto;
import main.systems.shop.core.persistence.entity.dto.ProductDto;
import main.systems.shop.core.persistence.entity.model.Order;
import main.systems.shop.core.persistence.entity.model.Product;
import main.systems.shop.core.persistence.services.ServiceCart;
import main.systems.shop.core.persistence.services.ServiceProduct;
import main.systems.shop.core.persistence.services.ServiceUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/api/v1/shop")
public class ShopController {
    @Autowired
    private ServiceProduct productService;
    @Autowired
    private ServiceCart cartService;

    @Autowired
    private ServiceUser userService;

    private CustomerConverter customerConverter;
    private static final Logger logger = LogManager.getLogger(ShopController.class);

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
        cartService.createNewCart();
        logger.info("New cart created: " + cartService);
    }


    @GetMapping("/list_cart")
    @ResponseBody
    private List<Order> list_cart() {
        return cartService.getCart();
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
        cartService.purgeProductById(productId);
    }

    @GetMapping("/productsCount")
    @ResponseBody
    public long getProductCount(@RequestParam Long productId) {
        Product product = productService.getProductsId(productId);
        return cartService.getCartProductCount(product);
    }

    @GetMapping("/admin/user_info")
    @ResponseBody
    public List<CustomerDto> getAllCustomers() {
        return userService.getAllCustomer().stream().map(customer -> new CustomerDto(customer)).toList();
    }
}
