package org.example.crud.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.crud.annotation.Id;
import org.example.crud.annotation.Table;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(value = "products")
public class Product {
    @Id
    private Long id;
    private String name;
    private String description;
}
