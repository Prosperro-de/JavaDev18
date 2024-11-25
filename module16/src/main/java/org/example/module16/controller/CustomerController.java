package org.example.module16.controller;

import lombok.AllArgsConstructor;
import org.example.module16.model.Customer;
import org.example.module16.model.dto.CustomerCreateRequest;
import org.example.module16.model.dto.CustomerResponse;
import org.example.module16.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/v1/customers")
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/{id}") // http://localhost:8080/api/v1/customers/1
    public CustomerResponse findById(@PathVariable Long id) {
        return customerService.findById(id);
    }

//    @GetMapping()// http://localhost:8080/api/v1/customers
//    public List<CustomerResponse> findAll() {
//        return customerService.findAll();
//    }

    @PostMapping()// http://localhost:8080/api/v1/customers
    @ResponseStatus(HttpStatus.CREATED)
    public Long createCustomer(@RequestBody CustomerCreateRequest request) {
        return customerService.createCustomer(request);
    }

    @PostMapping("/{id}/orders")// http://localhost:8080/api/v1/customers/{id}/orders
    public void createOrderForCustomer(@PathVariable  Long id, @RequestBody String orderRequestBody) {

    }
}
