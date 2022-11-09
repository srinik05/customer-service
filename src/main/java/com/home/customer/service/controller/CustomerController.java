package com.home.customer.service.controller;

import com.home.customer.service.entity.Customer;
import com.home.customer.service.service.CustomerService;
import com.home.customer.service.vo.ResponseTemplateVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer-service")
@Slf4j
public class CustomerController {

    @Autowired
    CustomerService service;

    @PostMapping("/")
    public Customer saveUser(@RequestBody Customer customer) {
        log.info("Inside saveUser of UserController");
        return service.saveUser(customer);
    }

    @GetMapping("/{id}")
    public ResponseTemplateVO getUserWithDepartment(@PathVariable("id") Long customerId) {
        log.info("Inside getUserWithDepartment of UserController");
        return service.getCustomerWithProduct(customerId);
    }

}
