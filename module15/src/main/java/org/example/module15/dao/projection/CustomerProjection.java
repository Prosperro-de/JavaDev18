package org.example.module15.dao.projection;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerProjection {
    private String firstName;
    private String lastName;
}
