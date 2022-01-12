package autopark.servise;

import autopark.bean.User;
import autopark.dao.exception.DAOException;
import autopark.servise.exception.ServiceException;

import java.util.List;

public interface UserService {
    User authorization(String login) throws  ServiceException;
    void registration(User user) throws ServiceException;
    void deleteUser(int id) throws ServiceException;
    List<User> findUsersByRole(String role) throws ServiceException;
    public User findUserById(int id) throws ServiceException;

}
