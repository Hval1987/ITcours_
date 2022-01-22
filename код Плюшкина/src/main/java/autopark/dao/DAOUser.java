package autopark.dao;

import autopark.bean.User;
import autopark.dao.exception.DAOException;
import autopark.servise.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

public interface DAOUser {
    User findUserByLogin(String login) throws DAOException;
    void registration(User user) throws DAOException;

    User findUser(int id ) throws DAOException;

    void  addUser(User user) throws DAOException, SQLException;

    void deleteUserById(int id) throws  DAOException;

    List<User> getUsersByRole(int idRole) throws  DAOException;


}
