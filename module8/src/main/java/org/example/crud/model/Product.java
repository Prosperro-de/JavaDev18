package org.example.crud.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.crud.annotation.Id;
import org.example.crud.annotation.Table;

@Data
@NoArgsConstructor
@Table(value = "products")
public class Product {
    @Id
    private Long id;
    private String name;
    private String description;
}
