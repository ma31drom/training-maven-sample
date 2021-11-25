package com.epam.repo.jdbc;

import com.epam.config.Configuration;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionPoolProvider {

    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

    static {

        Properties applicationProps = Configuration.getApplicationProps();

        config.setJdbcUrl(applicationProps.getProperty("jdbc.url"));
        config.setUsername(applicationProps.getProperty("jdbc.username"));
        config.setPassword(applicationProps.getProperty("jdbc.password"));
        config.setMaximumPoolSize(5);

        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        ds = new HikariDataSource(config);

    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    private ConnectionPoolProvider() {
    }

}
