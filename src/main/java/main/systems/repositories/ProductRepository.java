package main.systems.repositories;


import main.systems.data.HibernateUtils;
import main.systems.data.Product;
import main.systems.data.ProductRepositoryDao;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductRepository implements ProductRepositoryDao {

    @Autowired
    private final HibernateUtils hibernateUtils;

    public ProductRepository(HibernateUtils hibernateUtils) {
        this.hibernateUtils = hibernateUtils;
    }

    @Override
    public Product getProductsId(Long id) {
        try (Session session = hibernateUtils.getFactory()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            session.getTransaction().commit();
            return product;
        }
    }


    @Override
    public List<Product> getProducts() {
        try (Session session = hibernateUtils.getFactory()) {
            session.beginTransaction();
            List<Product> products = session.createQuery("select products from Product products").getResultList();
            session.getTransaction().commit();
            return products;
        }

    }

}

