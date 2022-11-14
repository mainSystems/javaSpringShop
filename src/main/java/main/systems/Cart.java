package main.systems;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Scope("prototype")
public class Cart {
    private Map<Product, Integer> cart = new HashMap<>();
    private static final Logger logger = LogManager.getLogger(Cart.class);


    public void delProduct(Product product, Integer count) {
        if (count == 0) {
            logger.info("Count of %s to work is 0, nothing to do \n", product.getTitle());
            return;
        }
        if (cart.containsKey(product)) {
            if (cart.get(product).compareTo(count) > 0) {
                cart.put(product, cart.get(product) - count);
            } else if (cart.get(product).compareTo(count) == 0) {
                logger.info("There are no more products of %s \n", product.getTitle());
                cart.remove(product);
            } else {
                logger.info("we cant delete more than we have");
                logger.info("products of %s will be removed \n", product.getTitle());
                cart.remove(product);
            }
        }
    }

    public void addProducts(Product product, Integer count) {
        if (product != null && count > 0) {
            cart.merge(product, count, Integer::sum);
            logger.info("cart = " + cart);
        }
    }

    public void listCart() {
        cart.forEach((key, value) -> System.out.println(key + " " + value));
    }

    public int getCartSize() {
        return cart.size();
    }

    public Map<Product, Integer> getCart() {
        return cart;
    }
}
