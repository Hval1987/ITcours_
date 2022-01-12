package autopark.servise.implement;


import autopark.bean.User;
import autopark.dao.DAOFactory;
import autopark.dao.exception.DAOException;
import autopark.servise.UserService;
import autopark.servise.exception.ServiceException;

import java.util.List;

public class UserServiceImpl implements UserService {


    @Override
    public User authorization(String login) throws ServiceException {

        try {
            if (login != null) {

                return DAOFactory.getInstance().getDAOUser().findUserByLogin(login);
            } else
                return null;
        } catch (DAOException exc) {
            System.out.println(exc);
            throw new ServiceException();
        }
    }

    @Override
    public void registration(User user) throws ServiceException {
        {
            if (user != null) {
                System.out.println(user);
                DAOFactory daoFactory = DAOFactory.getInstance();
                try {
                    daoFactory.getDAOUser().registration(user);
                } catch (DAOException e) {
                    e.printStackTrace();
                    throw new ServiceException();
                }
            }
        }
    }

    @Override
    public void deleteUser(int id) throws ServiceException {

        try {
            DAOFactory.getInstance().getDAOUser().deleteUserById(id);
        } catch (DAOException exc) {
            System.out.println("ошибка слоя DAO");
            throw new ServiceException();
        }

    }

    @Override
    public List<User> findUsersByRole(String role) throws ServiceException {
        List<User> list = null;
        try {
            int idRole = DAOFactory.getInstance().getDAORole().findRoleByTittle(role).getId();
            list = DAOFactory.getInstance().getDAOUser().getUsersByRole(idRole);
        } catch (DAOException exc) {
            System.out.println(exc);
            throw new ServiceException();
        }

        return list;
    }

    public User findUserById(int id) throws ServiceException {
        try {
            return DAOFactory.getInstance().getDAOUser().findUser(id);
        } catch (DAOException exc) {
            System.out.println(exc);
            throw new ServiceException();
        }
    }

}


