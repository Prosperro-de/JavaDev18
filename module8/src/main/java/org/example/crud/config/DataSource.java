package org.example.crud.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.experimental.UtilityClass;
import org.flywaydb.core.Flyway;

import java.sql.Connection;
import java.sql.SQLException;

@UtilityClass
public class DataSource {

    private static final String DB_URL = "jdbc:postgresql://localhost:5438/goit-dev18-lesson-db";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "password";

    private static final HikariConfig config = new HikariConfig();
    private static final HikariDataSource ds;

    static {
        config.setJdbcUrl(DB_URL);
        config.setUsername(DB_USER);
        config.setPassword(DB_PASSWORD);
        ds = new HikariDataSource(config);
        Flyway flyway = Flyway.configure()
                .dataSource(ds)
                .locations("db/migrations")
                .load();
        flyway.migrate();
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

//    public static Connection getConnection() throws SQLException {
//        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
//    }
}
