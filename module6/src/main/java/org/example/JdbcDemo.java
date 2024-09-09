package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

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
        customerDao.getAll().forEach(System.out::println);
//        customerDao.createDefaultCustomer();
    }
}
