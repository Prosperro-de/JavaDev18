package org.example.app.service;

import org.example.app.dao.GenericDao;
import org.example.app.model.Customer;
import org.example.app.util.RequestToEntityMapper;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class CustomerService {
    private GenericDao dao = new GenericDao();


    public void saveCustomer(Map<String, String[]> parameterMap) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Customer customer = RequestToEntityMapper.mapToEntity(parameterMap, Customer.class);
        System.out.println("customer = " + customer);
        dao.save(customer);
        System.out.println("customer = " + customer);
    }
}
