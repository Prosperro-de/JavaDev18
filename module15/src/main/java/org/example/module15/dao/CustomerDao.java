package org.example.module15.dao;

import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.example.module15.dao.projection.CustomerProjection;
import org.example.module15.exception.DatabaseException;
import org.example.module15.model.Customer;
import org.example.module15.model.Order;
import org.hibernate.SessionFactory;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.hibernate.query.criteria.JpaCriteriaQuery;
import org.hibernate.query.criteria.JpaPredicate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomerDao {
    private final SessionFactory sessionFactory;

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
//            var customer = session.find(Customer.class, id);
            var query = "SELECT c FROM Customer c "
                    + "LEFT JOIN FETCH c.orders o "
                    + "WHERE c.id = :id";
            var customer = session.createQuery(query, Customer.class)
                    .setParameter("id", id)
                    .getSingleResult();
            transaction.commit();
            return customer;
        }
    }

    public void addOrderToCustomer(Long customerId, Order order) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            var customer = session.find(Customer.class, customerId);
            customer.addOrder(order);
            transaction.commit();
        }
    }

    public void removeOrderFromCustomer(Long customerId, Long orderId) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            var customer = session.find(Customer.class, customerId);
            var order = session.find(Order.class, orderId);
            customer.removeOrder(order);
            transaction.commit();
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

    public List<Customer> findAll() {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            //SELECT * FROM customers
            var customersQuery = session.createQuery(
                    "from Customer", Customer.class);
//            customersQuery.setFirstResult(5);
//            customersQuery.setMaxResults(5);
            var customers = customersQuery.getResultList();
            transaction.commit();
            return customers;
        }
    }
}
