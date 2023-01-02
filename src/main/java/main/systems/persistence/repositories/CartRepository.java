package main.systems.persistence.repositories;

import main.systems.persistence.entity.model.Order;
import main.systems.persistence.entity.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Component
@Scope("prototype")
public class CartRepository {
    @Autowired
    protected CartRepositoryDao cartRepositoryDao;
    @Autowired
    protected CustomerRepositoryDao customerRepositoryDao;
    @Autowired
    protected ProductRepositoryDao productRepositoryDao;

    public CartRepository(CartRepositoryDao cartRepositoryDao, CustomerRepositoryDao customerRepositoryDao, ProductRepositoryDao productRepositoryDao) {
        this.cartRepositoryDao = cartRepositoryDao;
        this.customerRepositoryDao = customerRepositoryDao;
        this.productRepositoryDao = productRepositoryDao;
    }

    public boolean isOrderContainProductByKey(Product product) {
        return (cartRepositoryDao.findCountOrdersByProductId(product.getId()) > 0) ? true : false;
    }

    public long getCountProducts(Product product) {
        if (isOrderContainProductByKey(product)) {
            return cartRepositoryDao.findQuantityByProductId(product.getId());
        } else {
            return 0;
        }
    }

    @Transactional
    public void addProduct(Product product, Integer count) {
        Date date = new Date(System.currentTimeMillis());
        Order productToAdd;

        if (isOrderContainProductByKey(product)) {
            List<Order> productsToAdd = cartRepositoryDao.findOrderId(product.getId());
            productToAdd = cartRepositoryDao.getReferenceById(productsToAdd.get(0).getId());
            productToAdd.setQuantity(productToAdd.getQuantity() + count);
        } else {
            productToAdd = new Order();
            productToAdd.setProductsOrder(product);
            productToAdd.setQuantity(count);
            productToAdd.setDate(date);
            productToAdd.setCost(product.getCost());
            productToAdd.setCustomersOrder(customerRepositoryDao.getReferenceById((long) 1));
        }
        cartRepositoryDao.save(productToAdd);
    }

    @Transactional
    public void deleteProduct(Product product, Integer count) {
        List<Order> productsToDel = cartRepositoryDao.findOrderId(product.getId());
        Order productToDel = cartRepositoryDao.getReferenceById(productsToDel.get(0).getId());
        if (isOrderContainProductByKey(product)) {
            productToDel.setQuantity(productToDel.getQuantity() - count);
        }
        cartRepositoryDao.save(productToDel);
    }

    @Transactional
    public void purgeProduct(Product product) {
        List<Order> productsToPurge = cartRepositoryDao.findOrderId(product.getId());
        if (isOrderContainProductByKey(product)) {
            cartRepositoryDao.deleteById(productsToPurge.get(0).getId());
        }
    }

    public void purgeAllProduct() {
        cartRepositoryDao.deleteAll();
    }

    public List<Order> getCart() {
        return cartRepositoryDao.findAll();
    }
}
