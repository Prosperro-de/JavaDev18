package org.example.annotation;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Customer {
    private Long id;
    @Trim
    private String firstName;
    private String lastName;
    @Trim
    private String email;
    private String telNumber;
    private String postCode;
}
