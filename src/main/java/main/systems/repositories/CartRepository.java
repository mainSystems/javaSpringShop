package main.systems.repositories;

import main.systems.entity.Product;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Scope("prototype")
public class CartRepository {
    private final Map<Product, Integer> cart;

    public CartRepository() {
        this.cart = new HashMap<>();
    }

    public boolean isContainKey(Product product) {
        return (cart.containsKey(product));
    }

    public Integer getCountProducts(Product product) {
        return cart.get(product);
    }

    public void deleteProduct(Product product, Integer count) {
        cart.put(product, cart.get(product) - count);
    }

    public void purgeProduct(Product product) {
        cart.remove(product);
    }

    public void addProduct(Product product, Integer count) {
        cart.merge(product, count, Integer::sum);
    }

    public Map<Product, Integer> getCart() {
        return cart;
    }
}
