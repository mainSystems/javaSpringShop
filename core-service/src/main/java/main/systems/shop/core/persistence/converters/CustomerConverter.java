package main.systems.shop.core.persistence.converters;

import main.systems.shop.core.persistence.entity.model.dto.CustomerDto;
import main.systems.shop.core.persistence.entity.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerConverter {
    public Customer dtoToEntity(CustomerDto customerDto) {
        return new Customer(customerDto.getId(), customerDto.getTitle(), customerDto.getUsername(), customerDto.getPassword(), customerDto.getRoles());
    }

    public CustomerDto entityToDto(Customer customer) {
        return new CustomerDto(customer.getId(), customer.getTitle(), customer.getUsername(), customer.getPassword(), customer.getRoles());
    }
}
