package service;

import database.UserDao;
import model.NormalUser;
import model.Role;
import model.User;
import util.PasswordUtil;

public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean registerUser(String username, String password, String repeatPassword,String email) {
        if(!password.equals(repeatPassword)) return false;
        if(userDao.findByUsername(username) != null) return false;
        if(userDao.emailExists(email)) return false;

        String hashedPass = PasswordUtil.hashPassword(password);
        NormalUser user = new NormalUser(username, hashedPass, email, Role.USER);
        userDao.saveUser(user);

        return true;
    }

    public User loginUser(String username, String password) {
        User user = userDao.findByUsername(username);
        if(user == null) return null;

        return user.checkPassword(password) ? user : null;
    }
}
