package ui;

import java.util.Scanner;

public class Display {
    private Scanner sc = new Scanner(System.in);

    //Database messages

    public void showDatabaseConnectionSuccess() {
        System.out.println("Database successfully connected");
    }

    public void showDatabaseConnectionError(String errorMessage) {
        System.out.println("Database connection error. " + errorMessage);
    }

    //Menu section

    public String showMainMenu() {
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Quit");
        System.out.print("Enter your choice: ");
        return sc.nextLine();
    }

    public String askForUsername() {
        System.out.print("Enter your username: ");
        return sc.nextLine();
    }

    public String askForPassword() {
        System.out.print("Enter your password: ");
        return sc.nextLine();
    }

    public String askForRepeatPassword() {
        System.out.print("Repeat your password: ");
        return sc.nextLine();
    }

    public String askForEmail() {
        System.out.print("Enter your email: ");
        return sc.nextLine();
    }

    public void loginSuccess() {
        System.out.println("Login successful.");
    }

    public void loginError(String errorMessage) {
        System.out.println("Login error. " + errorMessage);
    }

    public void registerSuccess() {
        System.out.println("Register successful.");
    }

    public void registerError(String errorMessage) {
        System.out.println("Register error. " + errorMessage);
    }

}
