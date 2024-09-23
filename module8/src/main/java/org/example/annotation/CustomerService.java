package org.example.annotation;

import lombok.SneakyThrows;

import java.lang.reflect.Field;

public class CustomerService {

    public void printCustomerDetails(Customer customer) {
        processTrimAnnotation(customer);
        System.out.println(customer);
    }

    @SneakyThrows
    private void processTrimAnnotation(Customer customer) {
        Field[] declaredFields = customer.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (declaredField.isAnnotationPresent(Trim.class)) {
                declaredField.setAccessible(true);
                String value = (String) declaredField.get(customer);
                declaredField.set(customer, value.trim());
                declaredField.setAccessible(false);
            }
        }
    }
}
