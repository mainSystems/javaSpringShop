package main.systems.shop.core.controllers;

import lombok.extern.slf4j.Slf4j;
import main.systems.shop.api.entity.dto.CustomerDto;
import main.systems.shop.core.persistence.services.ServiceCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/v1/shop")
public class CustomerController {
    @Autowired
    ServiceCustomer serviceCustomer;

    @GetMapping("/customer/{customerId}")
    @ResponseBody
    public CustomerDto getCustomerById(@PathVariable(name = "customerId") Long customerId) {
        return new CustomerDto(serviceCustomer.getCustomerById(customerId));
    }
}
