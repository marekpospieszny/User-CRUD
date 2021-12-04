package pl.coderslab.entity;

import pl.coderslab.workshop2.BCrypt;
import pl.coderslab.workshop2.DBUtil;

import java.sql.*;
import java.util.Arrays;

public class UserDao extends User {

    private static final String CREATE_USER_QUERY = "INSERT INTO users(email, username, password) VALUES(?,?,?)";
    private static final String READ_ALL_USERS_QUERY = "SELECT * FROM users";
    private static final String READ_USER_QUERY = "SELECT * FROM users WHERE id = ?";
    private static final String READ_USER_EMAIL_QUERY = "SELECT * FROM users WHERE email = ?";
    private static final String UPDATE_USER_QUERY = "UPDATE users SET email = ?, username = ?, password = ? WHERE id = ?";
    private static final String DELETE_USER_QUERY = "DELETE FROM users WHERE id = ?";

    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

//  Dodawanie wcześniej stworzonego Usera do bazy danych i pobieranie id wygenerowanego w bazie danych:
    public User create(User user) {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getUserName());
            statement.setString(3, hashPassword(user.getPassword()));
            statement.executeUpdate();
            //Pobieramy wstawiony do bazy identyfikator, a następnie ustawiamy id obiektu user.
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
            return user;
        } catch (SQLException e) {
            System.out.println("Unable to establish connection with database.");
            e.printStackTrace();
            return null;
        }
    }

//  Wczytywanie danych z bazy danych i ustawianie ich do wskazanego Usera. Metoda wyszukuje po id:
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
            System.out.println("Unable to establish connection with database.");
            return null;
        }
    }

//  Wczytywanie danych z bazy danych i ustawianie ich do wskazanego Usera. Metoda wyszukuje po email:
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
            System.out.println("Unable to establish connection with database.");
            return null;
        }
    }

//  Aktualizacja danych na wskazanym Userze. Wcześniej należy pobrać Usera z bazy danych, aby mieć odpowiednie id:
    public void update(User user) {
        try (Connection connection = DBUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,user.getEmail());
            statement.setString(2,user.getUserName());
            statement.setString(3,hashPassword(user.getPassword()));
            statement.setInt(4,user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Unable to establish connection with database.");
        }

    }

//  Kasowanie rekordu z bazy danych na podstawie wskazanego id:
    public void delete(int userId) {
        try (Connection connection = DBUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_USER_QUERY);
            statement.setInt(1,userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Unable to establish connection with database.");
        }
    }

//  Metoda pomocnicza dynamicznie powiększająca tablicę Userów:
    private User[] addToArray(User u, User[] users) {
        User[] tmpUsers = Arrays.copyOf(users, users.length + 1); // Tworzymy kopię tablicy powiększoną o 1.
        tmpUsers[users.length] = u; // Dodajemy obiekt na ostatniej pozycji.
        return tmpUsers; // Zwracamy nową tablicę.
    }

//  Pobiera cała bazę danych users i zapisuje do wskazanej tablicy User[]:
    public User[] findAll () {
        try (Connection connection = DBUtil.getConnection()) {
            User[] allUsers = new User[0];
            PreparedStatement statement = connection.prepareStatement(READ_ALL_USERS_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setEmail(resultSet.getString(2));
                user.setUserName(resultSet.getString(3));
                user.setPassword(resultSet.getString(4));
                allUsers = addToArray(user,allUsers);
            }
            return allUsers;
        } catch (SQLException e) {
            System.out.println("Unable to establish connection with database.");
            return null;
        }
    }
}
