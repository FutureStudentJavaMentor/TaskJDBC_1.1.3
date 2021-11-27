package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static final Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {
    }

    public  void createUsersTable() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS mydatabase.users( " +
                    "id BIGINT  NOT NULL AUTO_INCREMENT , " +
                    " name VARCHAR(40) NOT NULL , " +
                    " lastName VARCHAR(40) NOT NULL ,  " +
                    "age TINYINT NOT NULL , " +
                    "PRIMARY KEY(id) ); ");
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException ex1) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            ex1.printStackTrace();
        }

    }

    public void dropUsersTable() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("USE mydatabase");
            preparedStatement.execute("DROP TABLE  IF EXISTS users ; ");
            connection.commit();
        } catch (SQLException ex1) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            ex1.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users ( name , LastName ,age ) VALUES (?,?,?);");

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.execute();

            connection.commit();
        } catch (SQLException ex1) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            ex1.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id = " + id);
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException ex1) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            ex1.printStackTrace();
        }

    }

    public List< User > getAllUsers() {
        List< User > users = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users ; ");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("LastName"));
                user.setAge(rs.getByte("age"));
                users.add(user);
            }

            connection.commit();
        } catch (SQLException ex1) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            ex1.printStackTrace();
        }

        return users;
    }

    public void cleanUsersTable() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(" TRUNCATE TABLE  users ;");
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException ex1) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            ex1.printStackTrace();
        }

    }
}