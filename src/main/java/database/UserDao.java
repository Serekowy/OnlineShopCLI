package database;

import model.Admin;
import model.NormalUser;
import model.Role;
import model.User;

import java.sql.*;

public class UserDao {
    private static final String DB_URL = "jdbc:sqlite:shop.db";

    public void saveUser(User user) {
        String sql = "INSERT INTO users (username, password, email, role) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getRole().toString());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";

        try(Connection conn = DriverManager.getConnection(DB_URL);
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String dbUsername = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String role = rs.getString("role");

                User user;

                if(role.equalsIgnoreCase("ADMIN")) {
                    user = new Admin(dbUsername, password, email, Role.ADMIN);
                } else {
                    user = new NormalUser(dbUsername, password, email, Role.USER);
                }

                user.setId(id);
                return user;
            }

            return null;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
