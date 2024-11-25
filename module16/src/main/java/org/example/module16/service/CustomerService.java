package org.example.module16.service;

import lombok.RequiredArgsConstructor;
import org.example.module16.exception.EntityNotFoundException;
import org.example.module16.mapper.CustomerMapper;
import org.example.module16.model.Customer;
import org.example.module16.model.dto.CustomerCreateRequest;
import org.example.module16.model.dto.CustomerResponse;
import org.example.module16.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
//@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerService(CustomerMapper customerMapper, CustomerRepository customerRepository) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
    }

    public CustomerResponse findById(Long id)  {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Customer with id " + id + " not found"));

        return customerMapper.toCustomerResponse(customer);
    }

    @Transactional
    public Long createCustomer(CustomerCreateRequest request) {
        Customer customer = customerMapper.toCustomer(request);
        customer.getCustomerDetails().setLoyaltyPoints(0);
        customer.getCustomerDetails().setCustomer(customer);
        return customerRepository
                .save(customer)
                .getId();
    }


}
