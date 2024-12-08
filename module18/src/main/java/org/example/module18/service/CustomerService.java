package org.example.module18.service;

import org.example.module18.exception.EntityNotFoundException;
import org.example.module18.mapper.CustomerMapper;
import org.example.module18.model.Customer;
import org.example.module18.model.dto.request.CustomerUpdateRequest;
import org.example.module18.model.dto.response.CustomerResponse;
import org.example.module18.repository.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
//@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerService(CustomerMapper customerMapper, CustomerRepository customerRepository) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
    }

    public CustomerResponse findById(Long id)  {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Customer with id " + id + " not found"));

        return customerMapper.toCustomerResponse(customer);
    }

    public Page<CustomerResponse> findAll(PageRequest pageRequest) {
        return customerRepository.findAll(pageRequest)
                .map(customerMapper::toCustomerResponse);
    }


    public CustomerResponse findByEmail(String email) {
        return customerMapper.toCustomerResponse(
                customerRepository.getCustomerByEmail(email)
                    .orElseThrow(() -> new EntityNotFoundException("Customer with email " + email + " not found")));
    }

    @Transactional
    public CustomerResponse updateCustomer(Long id, CustomerUpdateRequest request) {
        Customer customer = customerRepository.findForUpdateById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer with id " + id + " not found"));
        customer.setFirstName(request.firstName());
        customer.setLastName(request.lastName());
        customer.setTelNumber(request.telNumber());
        customer.setPostCode(request.postCode());
        if (customer.getCustomerDetails() != null) {
            customer.getCustomerDetails().setDateOfBirth(request.dateOfBirth());
            customer.getCustomerDetails().setLoyaltyPoints(request.loyaltyPoints());
        }
        return customerMapper.toCustomerResponse(customer);
    }

    public List<CustomerResponse> findForLastName(String lastName) {
        return customerMapper.toCustomerResponseList(
                customerRepository.findAll(
                        getByLastNameSpecification(lastName)));
    }

    private Specification<Customer> getByLastNameSpecification(String lastName) {
        return (root, query, builder) -> Optional.ofNullable(lastName)
                .map(name -> builder.equal(root.get("lastName"), lastName))
                .orElseGet(builder::conjunction);
    }
}
