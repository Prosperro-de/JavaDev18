package org.example;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Customer {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String telNumber;
    private String postCode;
}
