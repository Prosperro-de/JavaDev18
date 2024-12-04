package org.example.module17.controller;

import lombok.AllArgsConstructor;
import org.example.module17.model.dto.request.CustomerCreateRequest;
import org.example.module17.model.dto.request.CustomerUpdateRequest;
import org.example.module17.model.dto.response.CustomerResponse;
import org.example.module17.service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


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

//    @GetMapping// http://localhost:8080/api/v1/customers?email=
//    public CustomerResponse findByEmail(@RequestParam String email) {
//        return customerService.findByEmail(email);
//    }

//    @GetMapping// http://localhost:8080/api/v1/customers?lastName=
//    public List<CustomerResponse> findByLastName(@RequestParam String lastName) {
//        return customerService.findForLastName(lastName);
//    }

    @PutMapping("/{id}") // http://localhost:8080/api/v1/customers/1
    public CustomerResponse updateCustomer(@PathVariable Long id, @RequestBody CustomerUpdateRequest request) {
        return customerService.updateCustomer(id, request);
    }

    @GetMapping
    public Page<CustomerResponse> findAll(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return customerService.findAll(pageRequest);
//        return customerService.findAll();
    }
}
