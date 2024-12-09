package org.example.module18.service;

import lombok.RequiredArgsConstructor;
import org.example.module18.exception.EntityNotFoundException;
import org.example.module18.model.Customer;
import org.example.module18.model.CustomerDetails;
import org.example.module18.model.User;
import org.example.module18.model.dto.request.CustomerCreateRequest;
import org.example.module18.model.dto.request.UserLoginRequest;
import org.example.module18.repository.RoleRepository;
import org.example.module18.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public String createUser(CustomerCreateRequest request) {
        if (userRepository.existsByUserName(request.email())) {
            return "User already exists";
        }
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .telNumber(request.telNumber())
                .postCode(request.postCode())
                .build();

        CustomerDetails customerDetails = CustomerDetails.builder()
                .dateOfBirth(request.dateOfBirth())
                .loyaltyPoints(0)
                .customer(customer)
                .build();

        customer.setCustomerDetails(customerDetails);

        User user = User.builder()
                .userName(request.email())
                .password(passwordEncoder.encode(request.password()))
                .enabled(Boolean.TRUE)
                .customer(customer)
                .build();
        roleRepository.findByName("ROLE_USER")
                .ifPresent(user::addRole);
        userRepository.save(user);
        return "User created";
    }

    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName)
                .orElseThrow(() -> new EntityNotFoundException("User " + userName + " not found"));
    }

    public String login(UserLoginRequest request) {
        return null;
    }
}