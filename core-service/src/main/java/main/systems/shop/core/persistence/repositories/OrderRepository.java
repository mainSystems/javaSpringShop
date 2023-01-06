package main.systems.shop.core.persistence.repositories;

import main.systems.shop.core.persistence.entity.model.Customer;
import main.systems.shop.core.persistence.entity.model.Order;
import main.systems.shop.core.persistence.entity.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Component
@Scope("prototype")
public class OrderRepository {
    @Autowired
    protected OrderRepositoryDao orderRepositoryDao;

    public boolean isOrderContainProductByKey(Product product) {
        return (orderRepositoryDao.findCountOrdersByProductId(product.getId()) > 0) ? true : false;
    }

    public long getCountProducts(Product product) {
        if (isOrderContainProductByKey(product)) {
            return orderRepositoryDao.findQuantityByProductId(product.getId());
        } else {
            return 0;
        }
    }

    @Transactional
    public void addProduct(Product product, Integer count, Customer customer) {
        Date date = new Date(System.currentTimeMillis());
        Order productToAdd;

        if (isOrderContainProductByKey(product)) {
            List<Order> productsToAdd = orderRepositoryDao.findOrderId(product.getId());
            productToAdd = orderRepositoryDao.getReferenceById(productsToAdd.get(0).getId());
            productToAdd.setQuantity(productToAdd.getQuantity() + count);
        } else {
            productToAdd = new Order();
            productToAdd.setProductsOrder(product);
            productToAdd.setQuantity(count);
            productToAdd.setDate(date);
            productToAdd.setCost(product.getCost());
            productToAdd.setCustomersOrder(customer);
        }
        orderRepositoryDao.save(productToAdd);
    }

    @Transactional
    public void deleteProduct(Product product, Integer count) {
        List<Order> productsToDel = orderRepositoryDao.findOrderId(product.getId());
        Order productToDel = orderRepositoryDao.getReferenceById(productsToDel.get(0).getId());
        if (isOrderContainProductByKey(product)) {
            productToDel.setQuantity(productToDel.getQuantity() - count);
        }
        orderRepositoryDao.save(productToDel);
    }

    @Transactional
    public void purgeProduct(Product product) {
        List<Order> productsToPurge = orderRepositoryDao.findOrderId(product.getId());
        if (isOrderContainProductByKey(product)) {
            orderRepositoryDao.deleteById(productsToPurge.get(0).getId());
        }
    }

    public void purgeAllProduct() {
        orderRepositoryDao.deleteAll();
    }

    public List<Order> getOrder() {
        return orderRepositoryDao.findAll();
    }
}
