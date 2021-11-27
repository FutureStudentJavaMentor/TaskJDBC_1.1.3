package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static final String url = "jdbc:mysql://localhost:3306/mydatabase?useSSL=false ";
    private static final String userName = "root";
    private static final String password = "123";

    private static final SessionFactory sessionFactory;

    static {
        try {
            Properties properties = new Properties();
            properties.setProperty("hibernate.connection.url", url);
            properties.setProperty("hibernate.connection.username", userName);
            properties.setProperty("hibernate.connection.password", password);
            properties.setProperty("hibernate.show_hql", "true");
            properties.setProperty("org.hibernate.connection.autocommit","false");
            properties.setProperty("org.hibernate.dialect", "org.hibernate.dialect.MySQLDialect");

            properties.setProperty("hibernate.hbm2ddl.auto", "create");

            sessionFactory = new org.hibernate.cfg.Configuration()
                    .addProperties(properties)
                    .addPackage("jm.task.core.jdbc.model")
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory();
        } catch (Exception ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }


    public static Connection getConnection() {

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, userName, password);
            connection.setAutoCommit(false);
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return connection;
    }
}

