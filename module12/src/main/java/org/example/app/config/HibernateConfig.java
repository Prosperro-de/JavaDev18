package org.example.app.config;

import lombok.Getter;
import org.example.app.model.Customer;
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
