package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao {
    public static final String GET_ALL_CUSTOMERS_SQL =
            "SELECT * FROM customers";

    public static final String CREATE_CUSTOMER_SQL =
            "INSERT INTO customers(first_name, last_name, email, tel_number, post_code) " +
            "VALUES ('Mykola', 'Klushyn', 'mykola@gmail.com', '5555-55', '49000')";

    public void printAllCustomers() throws SQLException {
        Connection connection = DataSource.getConnection();
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_ALL_CUSTOMERS_SQL);
            while (resultSet.next()) {
//                System.out.println(resultSet.getLong("id")); // default - 0
                System.out.println(resultSet.getObject("id", Long.class)); // default - null
                System.out.println(resultSet.getString("first_name"));
                System.out.println(resultSet.getString("last_name"));
                System.out.println(resultSet.getString("email"));
                System.out.println(resultSet.getString("tel_number"));
                System.out.println(resultSet.getString("post_code"));
                System.out.println("______________________________________");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw  ex;
        }
    }

    public List<Customer> getAll() throws SQLException {
        Connection connection = DataSource.getConnection();
        List<Customer> result = new ArrayList<>();
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_ALL_CUSTOMERS_SQL);
            while (resultSet.next()) {
                result.add(Customer.builder()
                                .id(resultSet.getObject("id", Long.class))
                                .firstName(resultSet.getString("first_name"))
                                .lastName(resultSet.getString("last_name"))
                                .email(resultSet.getString("email"))
                                .postCode(resultSet.getString("tel_number"))
                                .telNumber(resultSet.getString("post_code"))
                        .build());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw  ex;
        }
        return result;
    }

    public void createDefaultCustomer() throws SQLException {
        Connection connection = DataSource.getConnection();
        try(Statement statement = connection.createStatement()) {
            statement.execute(CREATE_CUSTOMER_SQL);
        }
    }
}
