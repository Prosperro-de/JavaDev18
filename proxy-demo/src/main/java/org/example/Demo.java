package org.example;

import net.sf.cglib.proxy.Enhancer;

public class Demo {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(CustomerService.class);
        enhancer.setCallback(new TransactionalProxy());

//        CustomerService customerService = (CustomerService) enhancer.create();
        CustomerService customerService = new CustomerService();

        customerService.updateCustomer();
    }
}
