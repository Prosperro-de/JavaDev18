package org.example;

import lombok.SneakyThrows;
import org.example.prepared.CustomerDaoV2;
import org.example.statement.CustomerDao;

import java.util.List;
import java.util.Scanner;

public class CustomerService {
//    private CustomerDao customerDao = new CustomerDao();
    private CustomerDaoV2 customerDao = new CustomerDaoV2();
    private Scanner scanner = new Scanner(System.in);

    public Customer registerCustomer() {
        System.out.println("Please enter your name");
        String name = scanner.nextLine();
        System.out.println("Please enter your last name");
        String lastName = scanner.nextLine();
        System.out.println("Please enter your email");
        String email = scanner.nextLine();
        System.out.println("Please enter your phone number");
        String number = scanner.nextLine();
        System.out.println("Please enter your post code");
        String postCode = scanner.nextLine();

        Customer customer = Customer.builder()
                .firstName(name)
                .lastName(lastName)
                .email(email)
                .telNumber(number)
                .postCode(postCode)
                .build();
        customerDao.createCustomer(customer);
        return customer;
    }

    public List<Customer> registerCustomers(List<Customer> customers) {
        return customerDao.createCustomers(customers);
    }

    public List<Customer> findAllCustomers() {
        return customerDao.getAll();
    }
}
