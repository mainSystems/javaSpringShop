package main.systems.shop.api.entity.dto;


import main.systems.shop.api.entity.model.Customer;
import main.systems.shop.api.entity.model.Role;

import java.util.Collection;

public class CustomerDto {
    private Long id;
    private String title;
    private String username;
    private String password;
    private Collection<Role> roles;

    public CustomerDto() {
    }
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}
