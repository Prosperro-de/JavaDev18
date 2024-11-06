package org.example.module14.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.module14.model.Category;
import org.example.module14.model.Customer;
import org.example.module14.model.CustomerDetails;
import org.example.module14.model.Order;
import org.example.module14.model.OrderItem;
import org.example.module14.model.Product;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public SessionFactory getSessionFactory() {
        return new org.hibernate.cfg.Configuration()
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(CustomerDetails.class)
                .addAnnotatedClass(Order.class)
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(Category.class)
                .addAnnotatedClass(OrderItem.class)
                .buildSessionFactory();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
