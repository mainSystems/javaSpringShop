package main.systems.data;

import main.systems.entity.Order;
import main.systems.entity.Product;

import java.util.List;
import java.util.Map;

public interface CartRepositoryDao {
    boolean isContainKey(Product product);
    Integer getCountProducts(Product product);
    void deleteProduct(Product product, Integer count);
    void purgeProduct(Product product);
    void addProduct(Product product, Integer count);
    List<Order> getCart();

}
