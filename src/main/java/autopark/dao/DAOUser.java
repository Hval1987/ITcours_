package autopark.dao;

import autopark.bean.User;
import autopark.dao.exception.DAOException;
import autopark.servise.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

public interface DAOUser {
    User findUserByLogin(String login) throws DAOException;

    User findUserByName(String name) throws DAOException;

    void registration(User user) throws DAOException;

    User findUser(int id ) throws DAOException;

       void deleteUserById(int id) throws  DAOException;

    List<User> getUsersByRole(int idRole) throws  DAOException;


}
