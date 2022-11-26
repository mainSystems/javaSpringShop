package main.systems.data;

import main.systems.entity.Product;

import java.util.List;

public interface ProductRepositoryDao {
    Product getProductsById(Long id);
    List<Product> getProducts();
}
