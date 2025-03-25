package service;

import dao.UserDao;
import model.User;

public class UserService {
    private UserDao userDao;

    public UserService() {
        userDao = new UserDao();
    }

    public boolean registerUser(User user) {
        return userDao.addUser(user);
    }

    public User loginUser(String username, String password) {
        User user = userDao.getUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
    
    public User getUserById(int userId) {
        return userDao.getUserById(userId);
    }

    public boolean updateUser(User user) {
        return userDao.updateUser(user);
    }

    public boolean addUser(User user) {
        return userDao.addUser(user);
    }

    public boolean deleteUser(int userId) {
        return userDao.deleteUser(userId);
    }

}
