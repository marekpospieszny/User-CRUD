package pl.coderslab.entity;

public class UserDao extends User {

    private static final String CREATE_USER_QUERY = "INSERT INTO users(email, username, password) VALUES(?,?,?)";
    private static final String READ_ALL_USERS_QUERY = "SELECT * FROM users";
    private static final String READ_USER_QUERY = "SELECT * FROM users WHERE id = ?";
    private static final String UPDATE_EMAIL_QUERY = "UPDATE users SET email = ? WHERE id = ?";
    private static final String UPDATE_USERNAME_QUERY = "UPDATE users SET username = ? WHERE id = ?";
    private static final String UPDATE_PASSWORD_QUERY = "UPDATE users SET password = ? WHERE id = ?";
    private static final String DELETE_USER_QUERY = "DELETE FROM users WHERE id = ?";





}
