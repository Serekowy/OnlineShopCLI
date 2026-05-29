import database.DatabaseManager;
import database.UserDao;
import model.User;
import service.UserService;
import ui.Display;
import util.PasswordUtil;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Display display = new Display();
        DatabaseManager databaseManager = new DatabaseManager();
        UserDao userDao = new UserDao();
        UserService userService = new UserService(userDao);

        try {
            databaseManager.connectAndCreateTables();
            display.showDatabaseConnectionSuccess();
        } catch (SQLException e) {
            display.showDatabaseConnectionError(e.getMessage());
            return;
        }

        while (true) {
            String input = display.showMainMenu();

            switch(input) {
                case "1" -> {
                    String email = display.askForEmail();
                    String password = display.askForPassword();
                    String hashedPass = PasswordUtil.hashPassword(password);

                    User loggingUser = userService.loginUser(email, hashedPass);
                    if(loggingUser != null) {
                        display.loginSuccess();
                        loggingUser.runMenu();
                    } else {
                        display.loginError("Wrong email or password.");
                    }
                }
                case "2" -> {
                    String username = display.askForUsername();
                    String email = display.askForEmail();
                    String password = display.askForPassword();
                    String repeatPassword = display.askForRepeatPassword();

                    if(userService.registerUser(username, email, password, repeatPassword)) {
                        display.registerSuccess();
                    } else  {
                        display.registerError("Username or email already exists.");
                    }
                }
            }
        }

    }
}
