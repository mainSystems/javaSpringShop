package main.systems.controllers;

import main.systems.Cart;
import main.systems.CountProducts;
import main.systems.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/shop")
public class ShopController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private Cart cart;


    @RequestMapping(value = "/mainPage", method = RequestMethod.GET)
    private String getProducts(Model model) {
        model.addAttribute("productList", productRepository.getProducts());
        return "mainPage";
    }

    @RequestMapping(value = "/new_cart", method = RequestMethod.GET)
    private String new_cart(HttpServletRequest request) {
        Cart cart = new Cart();
        System.out.println("Создана новая корзина: " + cart);
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }


    @RequestMapping(value = "/list_cart", method = RequestMethod.GET)
    private String list_cart(Model model) {
        if (cart != null) {
            if (cart.getCartSize() == 0) {
                System.out.println("Корзина пуста: " + cart);
                model.addAttribute("info", "Cart is empty: " + cart);
                return "info";
            } else {
                System.out.println("Используемая корзина:  " + cart);
                model.addAttribute("listCart", cart.getCart());
            }
        }
        return "list_cart";
    }

    @GetMapping("/addProduct")
    public String addProductForm(Model model) {
        model.addAttribute("countProducts", new CountProducts());
        return "productForm";
    }

    @PostMapping("/addProduct")
    public String addProductSubmit(@ModelAttribute CountProducts greeting, Model model) {
        model.addAttribute("greeting", greeting);
        return "result";
    }


}
