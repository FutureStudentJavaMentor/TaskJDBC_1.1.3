package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String url = "jdbc:mysql://localhost:3306/mydatabase ";
    private static final String userName = "root";
    private static final String password = "123";



    public static Connection getConnection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection( url, userName,password);
            connection.setAutoCommit(false);
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return connection;
    }




}
