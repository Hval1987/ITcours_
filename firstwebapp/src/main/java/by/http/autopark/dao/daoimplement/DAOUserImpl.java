package by.http.autopark.dao.daoimplement;

import by.http.autopark.bean.User;
import by.http.autopark.dao.DAOUser;
import by.http.autopark.dao.connectionpool.ConnectionPool;
import by.http.autopark.dao.exception.ConnectionPoolException;
import by.http.autopark.dao.exception.DAOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * In this class, we implement the DAO interface
 * to access the database for operations with the user class
 */

public class DAOUserImpl implements DAOUser {


    private  static final Logger logger= LoggerFactory.getLogger(DAOUserImpl.class.getName());

        private static final String REQUEST_FOR_SELECT_ALL_USERS_BY_ROLE="SELECT*FROM park.user WHERE roles_id=?";
        static final String  REQUEST_FOR_SEARCH="SELECT * FROM park.user WHERE login=?";
        static String  REQUEST_FOR_SEARCH_BY_NAME="SELECT * FROM park.user WHERE name=?";
        private static final String  REQUEST_FOR_INSERT="INSERT INTO `park`.`user` " +
                "(`login`,`name`,`roles_id`,`email`,`passportNumber`,`userPassword`) VALUES(?,?,?,?,?,?)";
        private static final String REQUEST_FOR_DELETE_BY_ID="DELETE  FROM `park`.`user` WHERE id=?";
        private static final String REQUEST_FOR_SELECT_ALL_USERS ="SELECT * FROM park.user ";
        private static final String  REQUEST_FOR_SELECT_USER_BY_ID="SELECT * from park.user WHERE id=?";
        private static final String COLUMN_ID="id";
        private static final String COLUMN_LOGIN="login";
        private static final String COLUMN_ROLE_ID="roles_id";
        private static final String COLUMN_NAME="name";
        private static final String COLUMN_EMAIL="email";
        private static final String COLUMN_PASSPORT_NUMBER="passportNumber";
        private static final String COLUMN_USER_PASSWORD="userPassword";


        final ConnectionPool connect=ConnectionPool.getInstance();

    /**
     * in this method, we are looking for a user by login
     * @param login
     * @return instance of class user
     * @throws DAOException Exception of the DAO layer
     */
        @Override
        public User findUserByLogin(String login) throws DAOException {

            Connection con=null;
            PreparedStatement pstm=null;
            PreparedStatement ps=null;
            ResultSet rs=null;
            User user=new User();
            int id=0;

            try {
                con=ConnectionPool.getInstance().takeConnection();
                pstm=con.prepareStatement(REQUEST_FOR_SEARCH);
                pstm.setString(1,login);

                rs=pstm.executeQuery();
                logger.debug("комманда -"+REQUEST_FOR_SEARCH+" выполнена успешно");

                while(rs.next()){
                    user.setId(rs.getInt(COLUMN_ID));
                    user.setLogin(rs.getString(COLUMN_LOGIN));
                    user.setRoles_id(rs.getInt(COLUMN_ROLE_ID));
                    user.setName(rs.getString(COLUMN_NAME));
                    user.setEmail(rs.getString(COLUMN_EMAIL));
                    user.setPassportNumber(rs.getString(COLUMN_PASSPORT_NUMBER));
                    user.setPassword(rs.getString(COLUMN_USER_PASSWORD));

                }

            } catch (SQLException exc) {
                logger.warn("",exc);
                throw new DAOException(exc);
            }
            finally {
                try {
                    connect.closeConnection(con,pstm,rs);
                } catch (ConnectionPoolException e) {
                    e.printStackTrace();
                    throw new DAOException(e);
                }
            }
            return user;
        }

    /**
     * in this method we are looking for a user by name
     * @param name  name of user
     * @return instance of class user
     * @throws DAOException Exception of the DAO layer
     */
    @Override
    public User findUserByName(String name) throws DAOException {

            Connection con=null;
            PreparedStatement pstm=null;
            PreparedStatement ps=null;
            ResultSet rs=null;
            User user=new User();
            int id=0;

        try {
            con=connect.takeConnection();

            pstm=con.prepareStatement(REQUEST_FOR_SEARCH_BY_NAME);
            pstm.setString(1,name);
            rs=pstm.executeQuery();
            logger.debug("комманда -"+REQUEST_FOR_SEARCH_BY_NAME+" выполнена успешно");

            while(rs.next()){

                user.setId(rs.getInt(COLUMN_ID));
                user.setLogin(rs.getString(COLUMN_LOGIN));
                user.setRoles_id(rs.getInt(COLUMN_ROLE_ID));
                user.setName(rs.getString(COLUMN_NAME));
                user.setEmail(rs.getString(COLUMN_EMAIL));
                user.setPassportNumber(rs.getString(COLUMN_PASSPORT_NUMBER));
                user.setPassword(rs.getString(COLUMN_USER_PASSWORD));

            }

        } catch (SQLException exc) {
            logger.warn("",exc);
            throw new DAOException(exc);
        }
        finally {
            try {
                connect.closeConnection(con,pstm,rs);
            } catch (ConnectionPoolException e) {
                e.printStackTrace();
                throw new DAOException(e);
            }
            return user;
        }

    }

    /**
     *in this method, we register a new user in the database
     * @param user instance of class user
     * @throws DAOException Exception of the DAO layer
     */
    @Override
        public void registration(User user) throws DAOException {

            Connection con=null;
            PreparedStatement pstm=null;

            try {
                con=connect.takeConnection();
                pstm=con.prepareStatement(REQUEST_FOR_INSERT);

                pstm.setString(1,user.getLogin());
                pstm.setString(2,user.getName());
                pstm.setInt(3,user.getRoles_id());
                pstm.setString(4,user.getEmail());
                pstm.setString(5,user.getPassportNumber());
                pstm.setString(6,user.getPassword());

                pstm.executeUpdate();
                logger.debug("комманда -"+REQUEST_FOR_INSERT+" выполнена успешно");

            } catch (SQLException exc) {
                logger.warn("",exc);
                throw new DAOException();
            }
            finally {
                try {
                    connect.closeConnection(con,pstm);
                } catch (ConnectionPoolException e) {
                    e.printStackTrace();
                    throw new DAOException(e);
                }
            }

        }

    /**
     * in this method we are looking for a user by id
     * @param id
     * @return instance of class user
     * @throws DAOException Exception of the DAO layer
     */
        @Override
        public User findUser(int id) throws  DAOException {

            Connection con=null;
            PreparedStatement pstm=null;
            ResultSet rs=null;
            User user=null;

            try {
                con=connect.takeConnection();

                pstm=con.prepareStatement(REQUEST_FOR_SELECT_USER_BY_ID);
                pstm.setInt(1,id);
                rs=pstm.executeQuery();
                logger.debug("комманда -"+REQUEST_FOR_SELECT_USER_BY_ID+" выполнена успешно");
                user=new User();

                while(rs.next()){

                    user.setName(rs.getString(COLUMN_NAME));
                    user.setRoles_id(rs.getInt(COLUMN_ROLE_ID));
                    user.setEmail(rs.getString(COLUMN_EMAIL));
                    user.setPassportNumber(rs.getString(COLUMN_PASSPORT_NUMBER));
                    user.setId(rs.getInt(COLUMN_ID));
                    user.setPassword(rs.getString(COLUMN_USER_PASSWORD));

                }

            } catch (SQLException exc) {
                logger.warn("",exc);
                throw new DAOException(exc);
            }
            finally {
                try {
                    connect.closeConnection(con,pstm,rs);
                } catch (ConnectionPoolException e) {
                    e.printStackTrace();
                    throw new DAOException(e);
                }
                return user;
            }
        }

    /**
     * in this method, we remove the user from the database by id
     * @param id identifier of deleted user
     * @throws DAOException Exception of the DAO layer
     */
    @Override
        public void deleteUserById(int id) throws DAOException {

            Connection con=null;
            PreparedStatement pstm = null;
            try {
                con=connect.takeConnection();
                pstm=con.prepareStatement(REQUEST_FOR_DELETE_BY_ID);
                pstm.setInt(1,id);
                pstm.executeUpdate();
                logger.debug("комманда -"+REQUEST_FOR_DELETE_BY_ID+" выполнена успешно");

            } catch (SQLException exc) {
                logger.warn("",exc);
                throw new DAOException(exc);
            }
            finally {
                try {
                    connect.closeConnection(con,pstm);
                } catch (ConnectionPoolException e) {
                    e.printStackTrace();
                    throw new DAOException(e);
                }
            }
        }

    /**
     * in this method we get all users by role from data base
     * @param idRole
     * @return list of users
     * @throws DAOException Exception of the DAO layer
     */
    @Override
    public List<User> getUsersByRole(int idRole) throws DAOException {

            List<User> allUsers=new ArrayList<>();
            Connection con=null;
            Statement stm=null;
            ResultSet rs=null;
            PreparedStatement pstm=null;
            try{
                con=connect.takeConnection();
                pstm=con.prepareStatement(REQUEST_FOR_SELECT_ALL_USERS_BY_ROLE);
                pstm.setInt(1,idRole);
                rs=pstm.executeQuery();
                logger.debug("комманда -"+REQUEST_FOR_SELECT_ALL_USERS_BY_ROLE+" выполнена успешно");

                while (rs.next()) {
                    User user=new User();
                    user.setName(rs.getString(COLUMN_NAME));
                    user.setEmail(rs.getString(COLUMN_EMAIL));
                    user.setPassportNumber(rs.getString(COLUMN_PASSPORT_NUMBER));
                    user.setRoles_id(rs.getInt(COLUMN_ROLE_ID));
                    user.setId(rs.getInt(COLUMN_ID));

                    allUsers.add(user);
                    user = null;

                }


            } catch (SQLException exc) {
                logger.warn("",exc);
                throw new DAOException(exc);
            }
            finally {
                try {
                    connect.closeConnection(con,pstm,rs);
                } catch (ConnectionPoolException e) {
                    e.printStackTrace();
                    throw new DAOException(e);
                }
                return allUsers;
            }
        }
    }

