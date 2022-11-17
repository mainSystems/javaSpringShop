package main.systems.repositories;


import main.systems.data.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductRepository {
    private final int countInitProducts = 5;
    private final int minPrice = 200;
    private final int maxPrice = 400;
    private List<Product> products = new ArrayList<>();

    {
        for (int i = 0; i < countInitProducts; i++) {
            products.add(new Product((long) i, "Product_" + i, Math.floor(Math.random() * (maxPrice - minPrice + 1) + minPrice)));
        }
    }

    public void listAvailableProducts() {
        for (Product elem : products) {
            System.out.println(elem.toString());
        }
    }

    public Product getProductsId(int id) {
        return products.get(id);
    }

    public void setProducts(Product product) {
        products.add(product);
    }

    public List<Product> getProducts() {
        return products;
    }
}

