package autopark.servise.implement;


import autopark.bean.User;
import autopark.dao.DAOFactory;
import autopark.dao.exception.DAOException;
import autopark.servise.UserService;
import autopark.servise.exception.DataErrorException;
import autopark.servise.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Set;

/**
 * in this class we implement a service interface
 * for accessing and processing information about project users
 */

public class UserServiceImpl implements UserService {
    private static final Logger logger= LoggerFactory.getLogger(UserServiceImpl.class.getSimpleName());

    /**
     * In this method, we check the entered user data to log in
     * @param login
     * @param password
     * @return mark of successful entry
     * @throws ServiceException
     */
    @Override
    public boolean authorization(String login, String password) throws ServiceException {
        logger.debug("вход в систему пользователя "+login+password);

            if (login != null&& password !="") {
                User user;
                String passwordSQL;
                try {
                    logger.debug("идет поиск пользователя по логину в слое сервис");
                    user= DAOFactory.getInstance().getDAOUser().findUserByLogin(login);

                } catch (DAOException exc) {
                    logger.warn(" message ",exc);
                    throw new ServiceException(exc);
                }

                passwordSQL=user.getPassword();
                logger.debug("ищем пароль по экзепляру user ");

                if(password.equals(passwordSQL)){
                    logger.debug("пароль совпал");
                    return true;
                }
                else {
                    logger.info("пароль не совпал с логином");
                    return false;
                }

            } else
                logger.info("логин или пароль являются пустой строчкой");
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
        String login=user.getLogin().trim().replaceAll("\\s+","");
        String name=user.getName().trim().replaceAll("\\s+","");
        String email=user.getEmail().trim().replaceAll("\\s+","");
        String password=user.getPassword().trim().replaceAll("\\s+","");
        String passportNumber=user.getPassportNumber().trim().replaceAll("\\s+","");
        int roleID=user.getRoles_id();
        logger.info("попытка регистрации пользователя ->"+user);
        /**
         * Validate the entered data and, in case of an error,
         * generate a registration page with an error
         **/
        if(login.matches("[A-Za-z0-9]*")) {
            if (email.matches("^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$")) {
                if (name.matches("[A-ZА-Яa-zа-я]{3,30}")) {
                    if (passportNumber.matches("[A-Z][A-Z][0-9]{7}")) {
                        if (roleID > 0) {
                            if (password.matches("[A-Z0-9a-z]*")) {

                                DAOFactory daoFactory = DAOFactory.getInstance();
                                try {

                                    daoFactory.getDAOUser().registration(user);
                                } catch (DAOException exc) {
                                    logger.warn("",exc);
                                    throw new ServiceException(exc);
                                }
                            } else {
                                throw new DataErrorException("password entry error");
                            }
                        } else {
                            throw new DataErrorException("role selection error");
                        }
                    } else {
                        throw new DataErrorException("passport data entry error");
                    }
                } else {
                    throw new DataErrorException("name input error");
                }
            } else {
                throw new DataErrorException("email input error");
            }
        }

        else{
                throw new DataErrorException("login input error");
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
           logger.warn("",exc);
            throw new ServiceException(exc);
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
        logger.debug("поиск списка пользователей по роли");
        try {
            int idRole = DAOFactory.getInstance().getDAORole().findRoleByTittle(role).getId();
            logger.debug("получаем ID переданной роли -"+idRole);
            list = DAOFactory.getInstance().getDAOUser().getUsersByRole(idRole);
            logger.debug("получаем список пользователей по ID роли-> "+list);
        } catch (DAOException exc) {
           logger.warn(" ",exc);
            throw new ServiceException(exc);
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
            logger.debug("ищем пользователя по ID");
            return DAOFactory.getInstance().getDAOUser().findUser(id);
        } catch (DAOException exc) {
            logger.warn("",exc);
            throw new ServiceException(exc);
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
            logger.warn("",exc);
            throw new ServiceException(exc);
        }
    }
    /**
     * In this method we are looking for information about the user by name
     * @return found user
     * @throws ServiceException Exception of the DAO layer
     */
    public User findUserByName(String name) throws ServiceException {
        try {
            System.out.println(DAOFactory.getInstance().getDAOUser().findUserByName(name));
            return DAOFactory.getInstance().getDAOUser().findUserByName(name);

        } catch (DAOException exc) {
            logger.warn("",exc);
            throw new ServiceException(exc);
        }
    }

}


