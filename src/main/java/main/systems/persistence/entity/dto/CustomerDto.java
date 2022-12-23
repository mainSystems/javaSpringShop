package main.systems.persistence.entity.dto;

import lombok.Data;
import main.systems.persistence.entity.model.Customer;
import main.systems.persistence.entity.model.Role;

import java.util.Collection;

@Data
public class CustomerDto {
    private Long id;
    private String title;
    private String username;
    private String password;
    private Collection<Role> roles;

    public CustomerDto(Customer customer) {
        this.id = customer.getId();
        this.title = customer.getTitle();
        this.username = customer.getUsername();
        this.password = customer.getPassword();
        this.roles = customer.getRoles();
    }

    public CustomerDto(Long id, String title, String username, String password, Collection<Role> roles) {
        this.id = id;
        this.title = title;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
}
