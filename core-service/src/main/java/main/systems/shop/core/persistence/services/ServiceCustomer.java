package main.systems.shop.core.persistence.services;

import lombok.extern.slf4j.Slf4j;
import main.systems.shop.core.persistence.entity.model.Customer;
import main.systems.shop.core.persistence.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ServiceCustomer {
    @Autowired
    CustomerRepository customerRepository;

    public Customer getCustomerById(Long id) {
        return customerRepository.getCustomerById(id);
    }
}
