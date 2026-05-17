package jp.co.fanmill.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    private static final String URL =
            "jdbc:postgresql://localhost:5432/db_fanmill";

    private static final String USER = "postgres";

    private static final String PASSWORD = "postgres";

    private DBUtil() {
    }
    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("PostgreSQL JDBC Driver が見つかりません", e);
        }
    }
    public static Connection getConnection()
            throws SQLException {

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}