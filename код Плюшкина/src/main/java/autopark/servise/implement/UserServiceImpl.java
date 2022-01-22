package autopark.servise.implement;


import autopark.bean.User;
import autopark.dao.DAOFactory;
import autopark.dao.exception.DAOException;
import autopark.servise.UserService;
import autopark.servise.exception.DataErrorException;
import autopark.servise.exception.ServiceException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;

/**
 * in this class we implement a service interface
 * for accessing and processing information about project users
 */

public class UserServiceImpl implements UserService {

    /**
     * In this method, we check the entered user data to log in
     * @param login
     * @param password
     * @return mark of successful entry
     * @throws ServiceException
     */
    @Override
    public boolean authorization(String login, String password) throws ServiceException {

            if (login != null&& password !="") {
                User user;
                String passwordSQL;
                try {
                    System.out.println("service work auth");
                 user= DAOFactory.getInstance().getDAOUser().findUserByLogin(login);

                } catch (DAOException exc) {
                    System.out.println(exc);
                    throw new ServiceException();
                }

                passwordSQL=user.getPassword();

                if(password.equals(passwordSQL)){
                    System.out.println("пароль совпал");
                    return true;
                }
                else {
                    return false;
                }

            } else
                return false;
    }

    /**
     * in this method, we check the entered user data
     * and issue a command to the DAO layer to register the user in the database
     * @throws ServiceException Exception of the DAO layer
     * @throws DataErrorException this exception is thrown if the entered data is incorrect
     */
    @Override
    public void registration(User user) throws ServiceException, DataErrorException {
//        ValidatorFactory validatorFactory= Validation.buildDefaultValidatorFactory();
//        Validator validator=validatorFactory.getValidator();
//        Set<ConstraintViolation<User>> set=validator.validate(user);
//        System.out.println(set.size()+"set ");


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
            else{
                throw new DataErrorException();
            }
        }

    /**
     * check the id for correctness and give the command to delete it to the DAO layer
     * @param id
     * @throws ServiceException Exception of the DAO layer
     */
    @Override
    public void deleteUser(int id) throws ServiceException {

        try {
            DAOFactory.getInstance().getDAOUser().deleteUserById(id);
        } catch (DAOException exc) {
            System.out.println("ошибка слоя DAO");
            throw new ServiceException();
        }

    }

    /**
     * In this method, we find all users by a specific role
     * @param role the role by which we make the choice
     * @return list of user
     * @throws ServiceException Exception of the DAO layer
     */
    @Override
    public List<User> findUsersByRole(String role) throws ServiceException {
        List<User> list = null;
        try {
            int idRole = DAOFactory.getInstance().getDAORole().findRoleByTittle(role).getId();
            System.out.println("yser service -"+idRole);
            list = DAOFactory.getInstance().getDAOUser().getUsersByRole(idRole);
            System.out.println("list users --"+list);
        } catch (DAOException exc) {
            System.out.println(exc);
            throw new ServiceException();
        }

        return list;
    }

    /**
     * In this method, we are looking for a user by ID
     * @param id
     * @return found user
     * @throws ServiceException Exception of the DAO layer
     */
    public User findUserById(int id) throws ServiceException {
        try {
            return DAOFactory.getInstance().getDAOUser().findUser(id);
        } catch (DAOException exc) {
            System.out.println(exc);
            throw new ServiceException();
        }
    }

    /**
     * In this method we are looking for information about the user by login
     * @param login
     * @return found user
     * @throws ServiceException Exception of the DAO layer
     */
    public User findUserByLogin(String login) throws ServiceException {
        try {
            return DAOFactory.getInstance().getDAOUser().findUserByLogin(login);

        } catch (DAOException exc) {
            System.out.println(exc);
            throw new ServiceException();
        }
    }

}


