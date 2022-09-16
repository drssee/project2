package com.example.demo2.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public enum ConnectionUtil {
    INSTANCE;
    private HikariDataSource ds;

    ConnectionUtil(){
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://localhost:3306/todo?useUnicode=true&characterEncoding=utf8");
        config.setUsername("root");
        config.setPassword("rlaskagus1!");
        config.addDataSourceProperty("cachePrepStmts","true");
        config.addDataSourceProperty("prepStmtCacheSize","250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit","2048");

        ds = new HikariDataSource(config);
    }
    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

}
