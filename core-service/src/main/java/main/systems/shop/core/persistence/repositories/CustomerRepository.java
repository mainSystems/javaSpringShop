package main.systems.shop.core.persistence.repositories;

import main.systems.shop.api.entity.model.Customer;
import main.systems.shop.api.entity.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CustomerRepository {
    @Autowired
    protected CustomerRepositoryDao customerRepositoryDao;

    public CustomerRepository(CustomerRepositoryDao customerRepositoryDao) {
        this.customerRepositoryDao = customerRepositoryDao;
    }

    public Customer getCustomerById(Long customer_id) {
        return customerRepositoryDao.getReferenceById(customer_id);
    }

    public List<Order> getOrders() {
        return null;
    }

    public Optional<Customer> getCustomerByUserName(String username) {
        return customerRepositoryDao.findByUsername(username);
    }

    public List<Customer> getAllCustomers() {
        return customerRepositoryDao.findAll();
    }
}
