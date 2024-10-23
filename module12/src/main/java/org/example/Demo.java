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
//            Customer customer = session.find(Customer.class, 1L);
//            Customer customer1 = session.find(Customer.class, 1L);
//            session.detach(customer);
//            session.merge(customer);

//            customer.setFirstName("Mykola55");
//            session.remove(customer);
//            session.persist(customer);
//            System.out.println("customer = " + customer);
//            customer.setFirstName("Mykola2");

//            session.detach(customer);

            Customer customer = Customer.builder()
//                    .id(1223423L)
                    .firstName("Hiber")
                    .lastName("Nate")
                    .email("redhut10@gmail.com")
                    .telNumber("555-444")
                    .postCode("432-12")
                    .build();
            session.persist(customer);
            Customer customer1 = session.find(Customer.class, customer.getId());
//            customer.setFirstName("Mykola");
//            System.out.println("customer = " + customer);
//            session.persist(customer);
//            System.out.println("customer = " + customer);
//
//            System.out.println("customer = " + customer);
            transaction.commit();
        }

    }
}