package main.systems.persistence.repositories;


import main.systems.persistence.entity.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductRepository {
    @Autowired
    protected ProductRepositoryDao productRepositoryDao;

    public ProductRepository(ProductRepositoryDao productRepositoryDao) {
        this.productRepositoryDao = productRepositoryDao;
    }

    public Product getProductsById(Long product_id) {
        return productRepositoryDao.getReferenceById(product_id);
    }

    public List<Product> getProducts() {
        return productRepositoryDao.findAll();
    }
}

