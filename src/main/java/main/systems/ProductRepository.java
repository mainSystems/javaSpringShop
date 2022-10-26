package main.systems;


import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductRepository {
    private List<Product> products = new ArrayList<>();

    public void getProducts(int count) {
        if (count == -1) {
            for (Product elem : products) {
                System.out.println(elem.toString());
            }
        } else {
            System.out.println(products.get(count).toString());
        }
    }

    public void setProducts(Product product) {
        products.add(product);
    }
}
