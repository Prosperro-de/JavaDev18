package org.example.prepared;

import lombok.SneakyThrows;
import org.example.Customer;
import org.example.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoV2 {
    public static final String CREATE_CUSTOMER_SQL_TEMPLATE =
            "INSERT INTO customers(first_name, last_name, email, tel_number, post_code) " +
            "VALUES (?, ?, ?, ?, ?)";

    public static final String GET_ALL_CUSTOMERS_SQL =
            "SELECT * FROM customers";


    @SneakyThrows
    public void createCustomer(Customer customer) {
        Connection connection = DataSource.getConnection();
        try(PreparedStatement statement = connection.prepareStatement(CREATE_CUSTOMER_SQL_TEMPLATE,
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, customer.getFirstName());
            statement.setString(2, customer.getLastName());
            statement.setString(3, customer.getEmail());
            statement.setString(4, customer.getTelNumber());
            statement.setString(5, customer.getPostCode());

            statement.executeUpdate();
            ResultSet generatedKey = statement.getGeneratedKeys();
            while (generatedKey.next()) {
                customer.setId(generatedKey.getObject("id", Long.class));
            }
        }
    }

    @SneakyThrows
    public List<Customer> createCustomers(List<Customer> customers) {
        Connection connection = DataSource.getConnection();
        connection.setAutoCommit(false);
        try(PreparedStatement statement = connection.prepareStatement(CREATE_CUSTOMER_SQL_TEMPLATE,
                Statement.RETURN_GENERATED_KEYS)) {
            for (Customer customer : customers) {
                statement.setString(1, customer.getFirstName());
                statement.setString(2, customer.getLastName());
                statement.setString(3, customer.getEmail());
                statement.setString(4, customer.getTelNumber());
                statement.setString(5, customer.getPostCode());
                statement.addBatch();
            }
            statement.executeBatch();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            int counter = 0;
            while (generatedKeys.next()) {
                customers.get(counter)
                        .setId(generatedKeys.getObject("id", Long.class));
                counter++;
            }
            connection.commit();
        } catch (Exception ex) {
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
        return customers;
    }

    @SneakyThrows
    public List<Customer> getAll() {
        Connection connection = DataSource.getConnection();
        List<Customer> result = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement(GET_ALL_CUSTOMERS_SQL)) {
            ResultSet resultSet = statement.executeQuery();
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

}
