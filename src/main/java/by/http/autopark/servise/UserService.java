package by.http.autopark.servise;

import by.http.autopark.bean.User;
import by.http.autopark.servise.exception.DataErrorException;
import by.http.autopark.servise.exception.ServiceException;

import java.util.List;

/**
 * In this interface we define the behavior of User objects
 */
public interface UserService {
    boolean authorization(String login, String password) throws ServiceException;
    void registration(User user) throws ServiceException, DataErrorException;
    void deleteUser(int id) throws ServiceException;
    List<User> findUsersByRole(String role) throws ServiceException;
    public User findUserById(int id) throws ServiceException;
    public User findUserByLogin(String login) throws ServiceException;
    public User findUserByName(String login) throws ServiceException;

}
