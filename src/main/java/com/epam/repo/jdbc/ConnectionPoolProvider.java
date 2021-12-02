package com.epam.repo.jdbc;

import com.epam.config.Configuration;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionPoolProvider {

    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

    public static Connection getConnection() throws SQLException {
        if (ds == null) {
            Properties applicationProps = Configuration.getApplicationProps();

            config.setJdbcUrl(applicationProps.getProperty("jdbc.url"));
            config.setUsername(applicationProps.getProperty("jdbc.username"));
            config.setPassword(applicationProps.getProperty("jdbc.password"));
            config.setMaximumPoolSize(5);

            config.addDataSourceProperty("cachePrepStmts", "true");
            config.addDataSourceProperty("prepStmtCacheSize", "250");
            config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

            DriverManager.registerDriver(new Driver());

            ds = new HikariDataSource(config);
        }
        return ds.getConnection();
    }

    private ConnectionPoolProvider() {
    }

}
