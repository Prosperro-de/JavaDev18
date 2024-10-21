package org.example.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.app.annotation.Column;
import org.example.app.annotation.Entity;
import org.example.app.annotation.Id;
import org.example.app.annotation.Table;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(value = "customers")
public class Customer {
    @Id
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String email;
    @Column(name = "tel_number")
    private String telNumber;
    @Column(name = "post_code")
    private String postCode;
}
