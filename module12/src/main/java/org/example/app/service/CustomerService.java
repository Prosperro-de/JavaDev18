package org.example.app.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.app.dao.CustomerDao;
import org.example.app.model.Customer;
import org.example.app.util.RequestToEntityMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public class CustomerService {
    private CustomerDao dao = new CustomerDao();
    private ObjectMapper objectMapper = new ObjectMapper();

    public Customer findById(Long id)  {
        return dao.findById(id);

    }

    public List<Customer> findAll() {
        return dao.findAll();

    }

    public void saveCustomer(Map<String, String[]> parameterMap) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
//        Customer customer = RequestToEntityMapper.mapToEntity(parameterMap, Customer.class);
//        System.out.println("customer = " + customer);
//        dao.save(customer);
//        System.out.println("customer = " + customer);
    }

    public void saveCustomer(BufferedReader payload) throws IOException {
        Customer customer = objectMapper.readValue(payload, Customer.class);
        dao.saveCustomer(customer);
    }
}
