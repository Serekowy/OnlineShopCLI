import database.DatabaseManager;
import ui.Display;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Display display = new Display();
        DatabaseManager databaseManager = new DatabaseManager();

        try {
            databaseManager.connectAndCreateTables();
            display.showDatabaseConnectionSuccess();
        } catch (SQLException e) {
            display.showDatabaseConnectionError(e.getMessage());
            return;
        }
    }
}
