package org.example.module14.controller;

import lombok.AllArgsConstructor;
import org.example.module14.model.Customer;
import org.example.module14.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/findById")
    public ModelAndView findById(@RequestParam Long customerId) {
        ModelAndView modelAndView = new ModelAndView("index");
        Customer result = customerService.findById(customerId);
        modelAndView.addObject("action", "customerDetails");
        modelAndView.addObject("customer", result);
        return modelAndView;
    }

    @GetMapping("/findAllCustomers")
    public ModelAndView findAllCustomers() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("action", "allCustomerDetails");
        modelAndView.addObject("customers", customerService.findAll());
        return modelAndView;
    }

    @PostMapping("/createCustomer")
    @ResponseStatus(HttpStatus.CREATED)
    public ModelAndView createCustomer(@RequestBody Customer customer) {
        ModelAndView modelAndView = new ModelAndView("index");
        customerService.saveCustomer(customer);
        return modelAndView;
    }
}
