package main.systems.persistence.converters;

import main.systems.persistence.dto.CustomerDto;
import main.systems.persistence.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerConverter {
    public Customer dtoToEntity(CustomerDto customerDto){
        return new Customer(customerDto.getId(), customerDto.getTitle(),customerDto.getUsername(),customerDto.getPassword(), customerDto.getRoles());
    }

    public CustomerDto entityToDto(Customer customer){
        return new CustomerDto(customer.getId(), customer.getTitle(),customer.getUsername(),customer.getPassword(), customer.getRoles());
    }
}
