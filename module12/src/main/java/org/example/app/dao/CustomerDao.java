package org.example.app.dao;

import org.example.app.config.HibernateConfig;
import org.example.app.model.Customer;
import org.hibernate.SessionFactory;

import java.util.List;

public class CustomerDao {
    private final SessionFactory sessionFactory = HibernateConfig.getInstance().getSessionFactory();

    public void saveCustomer(Customer customer) {
        try(var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            session.persist(customer);
            transaction.commit();
        }
    }

    public Customer findById(Long id) {
        try(var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            var customer = session.find(Customer.class, id);
            transaction.commit();
            return customer;
        }
    }

    public List<Customer> findAll() {
        try(var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            //SELECT * FROM customers
            var customersQuery = session.createQuery(
                    "from Customer", Customer.class);
            customersQuery.setFirstResult(5);
            customersQuery.setMaxResults(5);
            var customers = customersQuery.getResultList();
            transaction.commit();
            return customers;
        }
    }
}
