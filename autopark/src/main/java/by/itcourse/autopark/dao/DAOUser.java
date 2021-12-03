package by.itcourse.autopark.dao;

import by.itcourse.autopark.bean.User;
import by.itcourse.autopark.dao.exception.DAOException;

import java.sql.SQLException;
import java.util.List;

public interface DAOUser {
    User findUser(int id ) throws SQLException, DAOException;

    void  addUser(User user) throws DAOException, SQLException;

    void deleteUserById(int id) throws SQLException, DAOException;

    List<User> getUsers() throws SQLException, DAOException;


}
