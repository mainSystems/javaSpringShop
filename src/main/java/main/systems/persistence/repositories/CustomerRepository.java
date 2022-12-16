package main.systems.persistence.repositories;

import main.systems.persistence.entity.Customer;
import main.systems.persistence.entity.Order;
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