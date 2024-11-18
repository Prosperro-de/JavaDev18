package org.example.module15.service;

import lombok.RequiredArgsConstructor;
import org.example.module15.dao.CustomerDao;
import org.example.module15.model.Customer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerDao dao;
    private final RestTemplate restTemplate;
//    @Value(value = "${api.token}")
    private final String apiKey;

    public CustomerService(CustomerDao dao,
                           @Qualifier(value = "paymentClient") RestTemplate restTemplate,
                           @Value("${payment.api.token}") String apiKey) {
//                            RestTemplate restTemplate) {
        this.dao = dao;
        this.restTemplate = restTemplate;
        this.apiKey = apiKey;
    }

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
