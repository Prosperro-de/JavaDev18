package org.example.module16.repository;

import jakarta.persistence.LockModeType;
import org.example.module16.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long>,
        JpaSpecificationExecutor<Customer> {
//    https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html
//    Optional<Customer> getCustomerByEmail(String email);

//    @Query("FROM Customer WHERE email = :email")
//    Optional<Customer> getCustomerByEmail(String email);
//
    @Query(nativeQuery = true, value = "SELECT * FROM customers c WHERE c.email = :email")
    Optional<Customer> getCustomerByEmail(@Param("email")String email);


//    https://vladmihalcea.com/postgresql-for-no-key-update/
    //FOR UPDATE or FOR NO KEY UPDATE
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT c FROM Customer c WHERE c.id = :id")
    Optional<Customer> findForUpdateById(Long id);
}
