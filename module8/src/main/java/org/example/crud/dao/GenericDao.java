package org.example.crud.dao;


import lombok.SneakyThrows;
import org.example.crud.config.DataSource;
import org.example.crud.model.Customer;
import org.example.crud.util.SqlGeneratorUtil;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GenericDao {


    @SneakyThrows
    public <T> T findById(Long id, Class<T> type) throws SQLException {
        String selectByIdQuery = SqlGeneratorUtil.createSelectByIdQuery(type);
        Connection connection = DataSource.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectByIdQuery)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return mapResultSetToObject(resultSet, type);
        }
    }

    @SneakyThrows
    private <T> T mapResultSetToObject(ResultSet resultSet, Class<T> type) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        T emptyObject = type.getConstructor().newInstance();
        for (Field declaredField : type.getDeclaredFields()) {
            declaredField.setAccessible(true);
            declaredField.set(emptyObject, resultSet.getObject(
                    SqlGeneratorUtil.resolveColumnName(declaredField)
            ));
            declaredField.setAccessible(false);
        }
        return emptyObject;

    }
}
