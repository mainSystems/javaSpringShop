package main.systems.data;

import java.util.List;

public interface ProductRepositoryDao {
    Product getProductsId(Long id);
    List<Product> getProducts();
}
