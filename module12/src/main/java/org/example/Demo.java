package org.example;

import org.example.app.config.HibernateConfig;
import org.example.app.model.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Demo {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateConfig.getInstance().getSessionFactory();

        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
//            Customer customer = session.get(Customer.class, 1L);
//            System.out.println("customer = " + customer);

            Customer customer = Customer.builder()
//                    .id(1223423L)
                    .firstName("Hiber")
                    .lastName("Nate")
                    .email("redhut3@gmail.com")
                    .telNumber("555-444")
                    .postCode("432-12")
                    .build();
            System.out.println("customer = " + customer);
            session.persist(customer);
            System.out.println("customer = " + customer);

            transaction.rollback();
        }

    }
}