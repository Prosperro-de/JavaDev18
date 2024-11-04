package org.example.app.config;

import lombok.Getter;
import org.example.app.model.Customer;
import org.example.app.model.CustomerDetails;
import org.example.app.model.Order;
import org.example.app.model.Product;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConfig {
    private static final HibernateConfig INSTANCE;

    @Getter
    private SessionFactory sessionFactory;

    static {
        INSTANCE = new HibernateConfig();
    }

    private HibernateConfig() {
        sessionFactory = new Configuration()
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(CustomerDetails.class)
                .addAnnotatedClass(Order.class)
//                .addAnnotatedClass(Product.class)
                .buildSessionFactory();
    }

    public static HibernateConfig getInstance() {
        return INSTANCE;
    }

    public void close() {
        sessionFactory.close();
    }
}
