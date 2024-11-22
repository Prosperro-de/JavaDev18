package org.example.module15.service;

import lombok.RequiredArgsConstructor;
import org.example.module15.dao.CustomerDao;
import org.example.module15.mapper.CustomerMapper;
import org.example.module15.model.Customer;
import org.example.module15.model.dto.CustomerResponse;
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
    private final CustomerMapper customerMapper;
    private final RestTemplate restTemplate;
//    @Value(value = "${api.token}")
    private final String apiKey;

    public CustomerService(CustomerDao dao, CustomerMapper customerMapper,
                           @Qualifier(value = "paymentClient") RestTemplate restTemplate,
                           @Value("${payment.api.token}") String apiKey) {
//                            RestTemplate restTemplate) {
        this.dao = dao;
        this.customerMapper = customerMapper;
        this.restTemplate = restTemplate;
        this.apiKey = apiKey;
    }

    public CustomerResponse findById(Long id)  {
        Customer customer = dao.findById(id);

        return customerMapper.toCustomerResponse(customer);
    }

    public List<CustomerResponse> findAll() {
        return dao.findAll().stream()
                .map(customerMapper::toCustomerResponse)
                .toList();
    }

    public void saveCustomer(Customer customer) {
        dao.saveCustomer(customer);
    }
}
