package org.example.crud;


import org.example.crud.dao.GenericDao;
import org.example.crud.model.Customer;
import org.example.crud.model.Product;

import java.sql.SQLException;

public class Demo {

    public static void main(String[] args) throws SQLException {
        GenericDao dao = new GenericDao();
        var object = dao.findById(1L, Customer.class);
        System.out.println("object  = " + object);
    }
}
