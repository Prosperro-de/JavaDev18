package org.example.module14.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.module14.dao.CustomerDao;
import org.example.module14.model.Customer;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerDao dao;

    public Customer findById(Long id)  {
        return dao.findById(id);

    }

    public List<Customer> findAll() {
        return dao.findAll();

    }

    public void saveCustomer(Customer customer) {
        dao.saveCustomer(customer);
    }
}
