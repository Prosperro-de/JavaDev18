package org.example;

import org.example.statement.CustomerDao;

import java.sql.SQLException;

public class JdbcDemo {
    public static final String CREATE_PAYMENT_TABLE_SQL = """
                        CREATE TABLE payments(
                id BIGSERIAL PRIMARY KEY,
                amount DECIMAL,
                customer_id BIGINT,
                FOREIGN KEY (customer_id) REFERENCES customers(id)
            )
            """;

    public static void main(String[] args) throws SQLException {
//        try(Connection connection = DataSource.getConnection();
//            Statement statement = connection.createStatement()) {
//            statement.execute(CREATE_PAYMENT_TABLE_SQL);
//        }

        CustomerDao customerDao = new CustomerDao();
//        customerDao.printAllCustomers();
//        customerDao.getAll().forEach(System.out::println);
//        customerDao.createDefaultCustomer();

        Customer customer = Customer.builder()
                .firstName("Oleg")
                .lastName("Shevchenko")
                .email("stepan63@gmail.com")
                .telNumber("12334455")
                .postCode("33-44")
                .build();
//        customer.setId(13L);
        customerDao.createCustomer(customer);
//        customerDao.updateCustomer(customer);

//        customerDao.deleteCustomerById(13L);
        String s ="";
    }
}
