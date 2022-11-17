package main.systems.controllers;

import main.systems.services.Cart;
import main.systems.data.CountProducts;
import main.systems.data.Product;
import main.systems.repositories.ProductRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/shop")
public class ShopController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private Cart cart;
    private static final Logger logger = LogManager.getLogger(ShopController.class);


    @RequestMapping(value = "/mainPage", method = RequestMethod.GET)
    @ResponseBody
    private List<Product> getProducts() {
        return productRepository.getProducts();
//    private String getProducts(Model model) {
//        model.addAttribute("productList", productRepository.getProducts());
//        return "mainPage";
    }

    @RequestMapping(value = "/new_cart", method = RequestMethod.GET)
    private String new_cart(HttpServletRequest request) {
        Cart cart = new Cart();
        logger.info("Создана новая корзина: " + cart);
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }


    @RequestMapping(value = "/list_cart", method = RequestMethod.GET)
    private String list_cart(Model model) {
        if (cart != null) {
            if (cart.getCartSize() == 0) {
                logger.info("Корзина пуста: " + cart);
                model.addAttribute("info", "Cart is empty: " + cart);
                return "info";
            } else {
                logger.info("Используемая корзина:  " + cart);
                model.addAttribute("listCart", cart.getCart());
            }
        }
        return "list_cart";
    }

    @GetMapping("/addProduct")
    public String addProductForm(@RequestParam String idProduct, Model model) {
        model.addAttribute("idProduct", idProduct);
        model.addAttribute("countProducts", new CountProducts());
        return "productForm";
    }

    @PostMapping("/addProduct")
    public String addProductSubmit(HttpServletRequest request, @ModelAttribute CountProducts countProducts, Model model) {
        String referer = request.getHeader("Referer");
        int count = countProducts.getCount();
        int productId = countProducts.getId();

        if (count == 0) {
            logger.info("Nothing to add in to cart: " + cart);
            model.addAttribute("info", "Nothing to add in to cart: " + cart);
            return "info";
        }

        String isDel = (count < 0) ? "del" : "add";
        switch (isDel) {
            case "add": {
                if (cart != null) {
                    Product product = productRepository.getProductsId(productId);
                    cart.addProducts(product, count);
                } else {
                    logger.info("Create cart first");
                }
                break;
            }
            case "del": {
                if (cart != null) {
                    Product product = productRepository.getProductsId(productId);
                    cart.delProduct(product, count * -1);
                } else {
                    logger.info("Create cart first");
                }
                break;
            }
        }

        return "redirect:" + referer;
    }

}
