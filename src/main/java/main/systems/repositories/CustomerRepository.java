package main.systems.repositories;

import main.systems.data.CustomerRepositoryDao;
import main.systems.data.HibernateUtils;
import main.systems.data.OrderRepositoryDao;
import main.systems.entity.Customer;
import main.systems.entity.Order;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerRepository implements CustomerRepositoryDao, OrderRepositoryDao {
    @Autowired
    private final HibernateUtils hibernateUtils;

    public CustomerRepository(HibernateUtils hibernateUtils) {
        this.hibernateUtils = hibernateUtils;
    }

    @Override
    public Customer getCustomerById(Long customer_id) {
        try (Session session = hibernateUtils.getFactory()) {
            session.beginTransaction();
            Customer customer = session.get(Customer.class, customer_id);
            System.out.println("customer = " + customer);
            session.getTransaction().commit();
            return customer;
        }
    }

    @Override
    public List<Order> getOrders() {
        return null;
    }
}
