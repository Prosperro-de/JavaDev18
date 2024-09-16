package org.example;


import java.util.List;

public class Demo {
    public static void main(String[] args) {
        CustomerService customerService = new CustomerService();
//        Customer customer = customerService.registerCustomer();
//        System.out.println("customer = " + customer);

        List<Customer> customerList = createCustomerList();
        customerService.registerCustomers(customerList);
        customerList.forEach(System.out::println);

    }

    private static List<Customer> createCustomerList() {
        return List.of(Customer.builder()
                        .firstName("Oleg")
                        .lastName("1")
                        .email("email234@gmail.com")
                        .telNumber("122342")
                        .postCode("555-444")
                .build(),
                Customer.builder()
                        .firstName("Oleg")
                        .lastName("2")
                        .email("email123432@gmail.com")
                        .telNumber("234")
                        .postCode("555-444")
                        .build(),
                Customer.builder()
                        .firstName("Oleg")
                        .lastName("4")
                        .email("email12312@gmail.com")
                        .telNumber("234234")
                        .postCode("555-444")
                        .build());
    }
}
