package org.example.annotation;

public class Demo {
    public static void main(String[] args) {

        CustomerService customerService = new CustomerService();
        Customer customer = Customer.builder()
                .firstName("  Mykola   ")
                .email("       mykola@gmail.com  ")
                .build();
        customerService.printCustomerDetails(customer);


    }
}
