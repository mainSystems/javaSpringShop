package main.systems.shop.core.controllers;

import lombok.extern.slf4j.Slf4j;
import main.systems.shop.api.entity.dto.CustomerDto;
import main.systems.shop.core.persistence.services.ServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/v1/shop")
public class AdminController {

    @Autowired
    private ServiceUser userService;


    @GetMapping("/admin/user_info")
    public List<CustomerDto> getAllCustomers() {
        return userService.getAllCustomer().stream().map(customer -> new CustomerDto(customer)).toList();
    }
}
