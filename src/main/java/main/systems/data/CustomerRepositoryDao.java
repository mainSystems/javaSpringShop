package main.systems.data;

import main.systems.entity.Customer;

public interface CustomerRepositoryDao {
    Customer getCustomerById (Long id);
}
