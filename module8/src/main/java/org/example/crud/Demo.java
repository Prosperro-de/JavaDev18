package org.example.crud;


import org.example.crud.dao.GenericDao;
import org.example.crud.model.Customer;
import org.example.crud.model.Product;

import java.sql.SQLException;

public class Demo {

    public static void main(String[] args) throws SQLException {
        GenericDao dao = new GenericDao();
//        var object = dao.findById(11L, Customer.class);
//        System.out.println("object  = " + object);

//        Customer mykola = Customer.builder()
//                .firstName("Mykola")
//                .lastName("Klushyn")
//                .email("mail@goit.com")
//                .telNumber("555-555-555")
//                .postCode("49000")
//                .build();
//
//        Product product = Product.builder()
//                .name("Phone")
//                .description("iPhone")
//                .build();
//        dao.save(mykola);
//        System.out.println("mykola = " + mykola);

//        dao.save(product);
//        System.out.println("product = " + product);

//        Customer mykolaForUpdate = Customer.builder()
//                .id(11L)
//                .firstName("Mykola")
//                .lastName("Klushyn")
//                .email("mail@goit.com")
//                .telNumber("333-444-555")
//                .postCode("49000")
//                .build();
        Customer mykolaForUpdate = dao.findById(11L, Customer.class);
        mykolaForUpdate.setTelNumber("777-777");
//        dao.update(mykolaForUpdate);
        dao.delete(mykolaForUpdate);
    }
}
