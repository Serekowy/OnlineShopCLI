package ui;

public class Display {

    public void showDatabaseConnectionSuccess() {
        System.out.println("Database successfully connected");
    }

    public void showDatabaseConnectionError(String errorMessage) {
        System.out.println("Database connection error. " + errorMessage);
    }

}
