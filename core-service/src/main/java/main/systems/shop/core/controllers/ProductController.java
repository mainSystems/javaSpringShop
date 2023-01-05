package main.systems.shop.core.controllers;

import lombok.extern.slf4j.Slf4j;
import main.systems.shop.api.dto.CountProductsDto;
import main.systems.shop.core.persistence.services.ServiceProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
@RequestMapping("/api/v1/shop")
public class ProductController {

    @Autowired
    private ServiceProduct productService;

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
}
