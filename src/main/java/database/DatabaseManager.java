package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:shop.db";

    public void connectAndCreateTables() throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            if (conn != null) {
                try (Statement stmt = conn.createStatement()) {
                    stmt.execute("PRAGMA foreign_keys = ON;");
                }
                createTables(conn);
            }
        }
    }

    private void createTables(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {

            String createUsersTable = """
                    CREATE TABLE IF NOT EXISTS users (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        username TEXT NOT NULL UNIQUE,
                        password TEXT NOT NULL,
                        email TEXT NOT NULL UNIQUE,
                        role TEXT NOT NULL
                    );
                    """;
            stmt.execute(createUsersTable);

            String createProductsTable = """
                    CREATE TABLE IF NOT EXISTS products (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        name TEXT NOT NULL,
                        price TEXT NOT NULL,
                        stockQuantity INTEGER NOT NULL,
                        category TEXT NOT NULL
                    );
                    """;
            stmt.execute(createProductsTable);

            String createOrdersTable = """
                    CREATE TABLE IF NOT EXISTS orders (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        userId INTEGER NOT NULL,
                        shippingMethod TEXT NOT NULL,
                        paymentMethod TEXT NOT NULL,
                        orderDate TEXT NOT NULL,
                        totalAmount TEXT NOT NULL,
                        FOREIGN KEY(userId) REFERENCES users(id)
                    );
                    """;
            stmt.execute(createOrdersTable);

            String createOrderTable = """
                    CREATE TABLE IF NOT EXISTS ordersItems (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        orderId INTEGER NOT NULL,
                        productId INTEGER NOT NULL,
                        quantity INTEGER NOT NULL,
                        unitPrice TEXT NOT NULL,
                        FOREIGN KEY(orderId) REFERENCES orders(id) ON DELETE CASCADE,
                        FOREIGN KEY(productId) REFERENCES products(id)
                    );
                    """;
            stmt.execute(createOrderTable);
        }
    }
}
