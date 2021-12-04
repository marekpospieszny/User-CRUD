package pl.coderslab.entity;

import pl.coderslab.workshop2.BCrypt;
import pl.coderslab.workshop2.DBUtil;

import java.sql.*;

public class UserDao extends User {

    private static final String CREATE_USER_QUERY = "INSERT INTO users(email, username, password) VALUES(?,?,?)";
    private static final String READ_ALL_USERS_QUERY = "SELECT * FROM users";
    private static final String READ_USER_QUERY = "SELECT * FROM users WHERE id = ?";
    private static final String READ_USER_EMAIL_QUERY = "SELECT * FROM users WHERE email = ?";
    private static final String UPDATE_EMAIL_QUERY = "UPDATE users SET email = ? WHERE id = ?";
    private static final String UPDATE_USERNAME_QUERY = "UPDATE users SET username = ? WHERE id = ?";
    private static final String UPDATE_PASSWORD_QUERY = "UPDATE users SET password = ? WHERE id = ?";
    private static final String DELETE_USER_QUERY = "DELETE FROM users WHERE id = ?";

    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public User create(User user) {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setString(3, hashPassword(user.getPassword()));
            statement.executeUpdate();
            //Pobieramy wstawiony do bazy identyfikator, a następnie ustawiamy id obiektu user.
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public User read(int userId) {
        try (Connection connection = DBUtil.getConnection()) {
            User user = new User();
            PreparedStatement statement = connection.prepareStatement(READ_USER_QUERY);
            statement.setInt(1,userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user.setId(resultSet.getInt(1));
                user.setEmail(resultSet.getString(2));
                user.setUserName(resultSet.getString(3));
                user.setPassword(resultSet.getString(4));
            }
            return user;
        } catch (SQLException e) {
            return null;
        }
    }

    public User read(String userEmail) {
        try (Connection connection = DBUtil.getConnection()) {
            User user = new User();
            PreparedStatement statement = connection.prepareStatement(READ_USER_EMAIL_QUERY);
            statement.setString(1,userEmail);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user.setId(resultSet.getInt(1));
                user.setEmail(resultSet.getString(2));
                user.setUserName(resultSet.getString(3));
                user.setPassword(resultSet.getString(4));
            }
            return user;
        } catch (SQLException e) {
            return null;
        }
    }

}
