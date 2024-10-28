package org.example.app.dao;

import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.example.app.config.HibernateConfig;
import org.example.app.dao.projection.CustomerProjection;
import org.example.app.exception.DatabaseException;
import org.example.app.model.Customer;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.hibernate.query.criteria.JpaCriteriaQuery;
import org.hibernate.query.criteria.JpaPredicate;
import org.hibernate.query.criteria.JpaRoot;

import java.util.List;

public class CustomerDao {
    private final SessionFactory sessionFactory = HibernateConfig.getInstance().getSessionFactory();

    public void saveCustomer(Customer customer) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            session.persist(customer);
            transaction.commit();
        }
    }

    public Customer findById(Long id) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            var customer = session.find(Customer.class, id);
            transaction.commit();
            return customer;
        }
    }

//    public Customer findByEmail(String email) {
//        try (var session = sessionFactory.openSession()) {
//            var transaction = session.beginTransaction();
//
//            var findByEmailQuery = session.createQuery(
//                    "FROM Customer WHERE email = :email", Customer.class);
//            findByEmailQuery.setParameter("email", email);
//            var result = findByEmailQuery.uniqueResultOptional()
//                    .orElseThrow(() ->
//                            new DatabaseException("Customer with email %s not found".formatted(email)));
//            transaction.commit();
//            return result;
//        }
//    }

//    public Customer findByEmail(String email) {
//        try(var session = sessionFactory.openSession()) {
//            HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
//
//            JpaCriteriaQuery<Customer> jpaCustomerQuery =
//                    criteriaBuilder.createQuery(Customer.class);
//
//            Root<Customer> root = jpaCustomerQuery.from(Customer.class);
//
//            JpaPredicate emailPredicate =
//                    criteriaBuilder.equal(root.get("email"), email);
//
//            jpaCustomerQuery.select(root).where(emailPredicate);
//
//            return session.createQuery(jpaCustomerQuery).uniqueResultOptional()
//                    .orElseThrow(() ->
//                            new DatabaseException("Customer with email %s not found".formatted(email)));
//        }
//    }

    public CustomerProjection findByEmail(String email) {
        try (var session = sessionFactory.openSession()) {
            HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

            JpaCriteriaQuery<CustomerProjection> jpaCustomerQuery =
                    criteriaBuilder.createQuery(CustomerProjection.class);

            Root<Customer> root = jpaCustomerQuery.from(Customer.class);

            JpaPredicate emailPredicate =
                    criteriaBuilder.equal(root.get("email"), email);

            //SELECT * FROM customers WHERE email = ?
            //SELECT s.first_name, s.last_name FROM customers s WHERE email = ?
            jpaCustomerQuery.select(criteriaBuilder.construct(
                    CustomerProjection.class,
                    root.get("firstName"), root.get("lastName")
            )).where(emailPredicate);

            return session.createQuery(jpaCustomerQuery).uniqueResultOptional()
                    .orElseThrow(() ->
                            new DatabaseException("Customer with email %s not found".formatted(email)));


        }
    }


    public static void main(String[] args) {
        CustomerDao customerDao = new CustomerDao();

        System.out.println("customerDao.findByEmail(\"alice.johnson@example.com\") = " + customerDao.findByEmail("alice.johnson@example.com"));
    }

    public List<Customer> findAll() {
        try (var session = sessionFactory.openSession()) {
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
