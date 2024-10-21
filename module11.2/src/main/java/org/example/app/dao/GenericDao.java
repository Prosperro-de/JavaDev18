package org.example.app.dao;


import lombok.SneakyThrows;
import org.example.app.annotation.Id;
import org.example.app.config.DataSource;
import org.example.app.util.SqlGeneratorUtil;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenericDao {

    @SneakyThrows
    public <T> T findById(Long id, Class<T> type) {
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
    public <T> List<T> findAll(Class<T> type) {
        String selectAllQuery = SqlGeneratorUtil.createSelectAllQuery(type);
        Connection connection = DataSource.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectAllQuery)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<T> resultList = new ArrayList<>();
            while (resultSet.next()) {
                resultList.add(mapResultSetToObject(resultSet, type));
            }
            return resultList;
        }
    }

    @SneakyThrows
    public <T> void save(T object) {
        String insertQuery = SqlGeneratorUtil.createInsertQuery(object.getClass());
        Connection connection = DataSource.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery,
                Statement.RETURN_GENERATED_KEYS)) {
            connection.setAutoCommit(false);
            List<Field> insertableFields = getInsertableFields(object);
            setValuesIntoPreparedStatement(preparedStatement, object, insertableFields);
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            while (generatedKeys.next()) {
                insertIdValue(object, generatedKeys);
            }
            connection.commit();
        } catch (Exception ex) {
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
    }

    @SneakyThrows
    public <T> void update(T object) {
        String updateQuery = SqlGeneratorUtil.createUpdateQuery(object.getClass());
        Connection connection = DataSource.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            connection.setAutoCommit(false);

            List<Field> insertableFields = getInsertableFields(object);
            setValuesIntoPreparedStatement(preparedStatement, object, insertableFields);

            Field idField = getIdField(object);
            idField.setAccessible(true);
            preparedStatement.setObject(insertableFields.size() + 1,
                    idField.get(object));
            idField.setAccessible(false);

            System.out.println("preparedStatement = " + preparedStatement);
            int updatedLines = preparedStatement.executeUpdate();
            if (updatedLines != 1) {
                throw new RuntimeException("Failed to update " + object.getClass().getSimpleName());
            }
            connection.commit();
        }  catch (Exception ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    @SneakyThrows
    public <T> void delete(T object) {
        String deleteQuery = SqlGeneratorUtil.createDeleteQuery(object.getClass());
        Connection connection = DataSource.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            connection.setAutoCommit(false);
            Field idField = getIdField(object);
            idField.setAccessible(true);
            preparedStatement.setObject(1, idField.get(object));
            idField.setAccessible(false);

            int deletedLines = preparedStatement.executeUpdate();
            if (deletedLines != 1) {
                throw new RuntimeException("Failed to delete " + object.getClass().getSimpleName());
            }

            connection.commit();
        } catch (Exception ex) {
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
    }

    @SneakyThrows
    private <T> void setValuesIntoPreparedStatement(PreparedStatement preparedStatement, T object,
                                                    List<Field> insertableFields) {
        for (int i = 0; i < insertableFields.size(); i++) {
            Field field = insertableFields.get(i);
            field.setAccessible(true);
            preparedStatement.setObject(i + 1, field.get(object));
            field.setAccessible(false);
        }
    }

    private static <T> List<Field> getInsertableFields(T object) {
        return Arrays.stream(object.getClass().getDeclaredFields())
                .filter(field -> !field.isAnnotationPresent(Id.class))
                .toList();
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

    private static <T> void insertIdValue(T object, ResultSet generatedKeys) throws IllegalAccessException, SQLException {
        Field idField = getIdField(object);
        idField.setAccessible(true);
        idField.set(object, generatedKeys.getObject(1, Long.class));
        idField.setAccessible(false);
    }

    private static <T> Field getIdField(T object) {
        return Arrays.stream(object.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Id.class))
                .findAny()
                .orElseThrow();
    }
}
