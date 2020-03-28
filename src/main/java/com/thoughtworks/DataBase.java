package com.thoughtworks;

import java.sql.*;


public class DataBase {
    private static final String URL = "jdbc:mysql://localhost:3306/thoughtworks" +
            "?useUnicode=true&characterEncoding=utf-8&serverTimezone=Hongkong";
    private static final String ADMIN = "root";
    private static final String ADMIN_KEY = "mysql";

    public static void createDB() {
        try (Connection connection = DriverManager.getConnection(URL, ADMIN, ADMIN_KEY)) {
            String selectSql = "SHOW TABLES LIKE 'user_info'";
            try (PreparedStatement statement = connection.prepareStatement(selectSql)) {
                if (statement.executeUpdate(selectSql) > -1) {
                    String createEql = "CREATE TABLE user_info(" +
                            "name VARCHAR(10) NOT NULL," +
                            "phone VARCHAR(11) NOT NULL," +
                            "email VARCHAR(50) NOT NULL," +
                            "password VARCHAR(16) NOT NULL," +
                            "tries INT DEFAULT 0 NOT NULL," +
                            "PRIMARY KEY (name)" +
                            ")ENGINE = InnoDB CHARSET=utf8";
                    try (PreparedStatement createStatement = connection.prepareStatement(createEql)) {
                        createStatement.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void register(User user) {
        try (Connection connection = DriverManager.getConnection(URL, ADMIN, ADMIN_KEY)) {
            String sql = "INSERT INTO user_info (name, phone, email, password, tries) VALUES(?,?,?,?,?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, user.getName());
                statement.setString(2, user.getPhone());
                statement.setString(3, user.getEmail());
                statement.setString(4, user.getPasswords());
                statement.setInt(5, user.getTries());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static User login(String name, String passwords) {

        try (Connection connection = DriverManager.getConnection(URL, ADMIN, ADMIN_KEY)) {
            String sql = "SELECT name, phone, email, password, tries FROM user_info WHERE name = ? AND password = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, name);
                statement.setString(2, passwords);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.first()) {
                    return new User(resultSet.getString("name"), resultSet.getString("phone"),
                            resultSet.getString("email"), resultSet.getString("password"),
                            resultSet.getInt("tries"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void updateTries(String name, int tries) {
        try (Connection connection = DriverManager.getConnection(URL, ADMIN, ADMIN_KEY)) {
            String sql = "UPDATE user_info SET tries = ? WHERE name = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, tries);
                statement.setString(2, name);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getTries(String name) {
        try (Connection connection = DriverManager.getConnection(URL, ADMIN, ADMIN_KEY)) {
            String sql = "SELECT tries FROM user_info WHERE name = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, name);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    return resultSet.getInt("tries");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static boolean checkNameExits(String name) {
        try (Connection connection = DriverManager.getConnection(URL, ADMIN, ADMIN_KEY)) {
            String sql = "SELECT name FROM user_info WHERE name = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, name);
                return statement.executeQuery().first();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
