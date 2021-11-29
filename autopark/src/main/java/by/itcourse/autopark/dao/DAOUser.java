package by.itcourse.autopark.dao;

import by.itcourse.autopark.bean.User;
import by.itcourse.autopark.dao.exceptions.DAOException;

import java.sql.SQLException;
import java.util.List;

public interface DAOUser {
    User findUser(int id ) throws SQLException;

    void  addUser(User user) throws DAOException, SQLException;

    void deleteUserById(int id) throws SQLException;

    List<User> getUsers() throws SQLException;


}
