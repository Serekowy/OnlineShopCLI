package model;

public class NormalUser extends User{

    public NormalUser(String username, String password, String email, Role role) {
        super(username, password, email, role);
    }

    @Override
    public void runMenu() {
        System.out.println("Hello, " + getUsername());
    }
}
