package main.systems;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Scope("prototype")
public class Cart {
    private Map<Product, Integer> cart = new HashMap<>();


    public void delProduct(Product product, Integer count) {
        if (count == 0) {
            System.out.printf("Count of %s to work is 0, nothing to do \n", product.getTitle());
            return;
        }
        if (cart.containsKey(product)) {
            if (cart.get(product).compareTo(count) > 0) {
                cart.put(product, cart.get(product) - count);
            } else if (cart.get(product).compareTo(count) == 0) {
                System.out.printf("There are no more products of %s \n", product.getTitle());
                cart.remove(product);
            } else {
                System.out.println("we cant delete more than we have");
                System.out.printf("products of %s will be removed \n", product.getTitle());
                cart.remove(product);
            }
        }
    }

    public void addProducts(Product product, Integer count) {
        if (product != null && count > 0) {
            cart.merge(product, count, Integer::sum);
            System.out.println("cart = " + cart);
        }
    }

    public void listCart() {
        cart.forEach((key, value) -> System.out.println(key + " " + value));
    }

    public int getCartSize() {
        return cart.size();
    }

}
