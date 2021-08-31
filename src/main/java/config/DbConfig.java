package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConfig {

    public static final String dbUser = "postgres";
    public static final String dbPassword = "Stormshadow007";
    public static String url;
    public static String host = "localhost";
    public static String dbName = "JdbcJava";
    public static String port = "5432";

    public DbConfig() {
    }

    public static Connection getConnection() {
        Connection connection = null;
        url = "jdbc:postgresql://" + host + ":" + port + "/" + dbName;

        try {
            connection = DriverManager.getConnection(url, dbUser, dbPassword);
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return connection;
    }
}
