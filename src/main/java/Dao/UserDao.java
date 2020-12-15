package Dao;

import model.User;

import java.util.List;

public interface UserDao {
    void saveUser(User user);
    List<User> getAllUsers();
    User findUserById(int id);
    void deleteUserById(int id);
    void clearUsers();
    List<User> getUsersByDepartmentId(int id);
}
