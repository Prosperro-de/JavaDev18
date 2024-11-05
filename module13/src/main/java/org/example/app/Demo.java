package org.example.app;

import org.example.app.dao.CustomerDao;
import org.example.app.model.Customer;
import org.example.app.model.Order;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Demo {
    public static void main(String[] args) {
//        addOrderToCustomerDemo();
        removeOrderFromCustomerDemo();
    }

    public static void addOrderToCustomerDemo() {
        CustomerDao customerDao = new CustomerDao();
//        Customer customer = customerDao.findById(1L);
        Order order = Order.builder()
                .orderDate(LocalDate.now())
                .status("NEW")
                .totalPrice(BigDecimal.TEN)
                .build();

//        customer.addOrder(order);
        customerDao.addOrderToCustomer(2L, order);
    }


    public static void removeOrderFromCustomerDemo() {
        CustomerDao customerDao = new CustomerDao();
        customerDao.removeOrderFromCustomer(2L, 33L);
    }
}
