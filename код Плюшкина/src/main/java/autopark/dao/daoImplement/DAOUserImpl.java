package autopark.dao.daoImplement;

import autopark.bean.User;
import autopark.dao.DAOUser;
import autopark.dao.connectionPool.ConnectionPool;
import autopark.dao.exception.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class DAOUserImpl implements DAOUser {
        private static final String REQUEST_FOR_SELECT_ALL_USERS_BY_ROLE="SELECT*FROM park.user WHERE roles_id=?";
        private static final String  REQUEST_FOR_SEARCH="SELECT * FROM park.user WHERE login=?";
        private static final String  REQUEST_FOR_INSERT="INSERT INTO `park`.`user` " +
                "(`login`,`name`,`roles_id`,`email`,`passportNumber`,`userPassword`) VALUES(?,?,?,?,?,?)";
        private static final String REQUEST_FOR_DELETE_BY_ID="DELETE  FROM `park`.`user` WHERE id=?";
        private static final String REQUEST_FOR_SELECT_ALL_USERS ="SELECT * FROM park.user ";
        private static final String  REQUEST_FOR_SELECT_USER_BY_ID="SELECT * from park.user WHERE id=?";

        final ConnectionPool connect=ConnectionPool.getInstance();


        @Override
        public User findUserByLogin(String login) throws DAOException {

            System.out.println("login dao");

            Connection con=null;
            PreparedStatement pstm=null;
            PreparedStatement ps=null;
            ResultSet rs=null;
            User user=new User();
            int id=0;

            try {
                con=connect.takeConnection();

                pstm=con.prepareStatement(REQUEST_FOR_SEARCH);
                pstm.setString(1,login);

                rs=pstm.executeQuery();
                System.out.println("dao work");


                while(rs.next()){
                    user.setId(rs.getInt("id"));
                    user.setLogin(rs.getString("login"));
                    user.setRoles_id(rs.getInt("roles_id"));
                    user.setName(rs.getString("name"));


                    user.setEmail(rs.getString("email"));

                    user.setPassportNumber(rs.getString("passportNumber"));


                    user.setPassword(rs.getString("userPassword"));

                }

            } catch (SQLException e) {
                e.printStackTrace();
                throw new DAOException();
            }
            finally {
                connect.closeConnection(con,pstm,rs);
            }
            System.out.println("dao -"+user);
            return user;
        }
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


            } catch (SQLException e) {
                e.printStackTrace();
                throw new DAOException();
            }
            finally {
                connect.closeConnection(con,pstm);
            }

        }

        @Override
        public User findUser(int id) throws  DAOException {
            System.out.println("login dao");

            Connection con=null;
            PreparedStatement pstm=null;
            ResultSet rs=null;
            User user=null;

            try {
                con=connect.takeConnection();
                System.out.println("id--"+id);

                pstm=con.prepareStatement(REQUEST_FOR_SELECT_USER_BY_ID);
                pstm.setInt(1,id);
                rs=pstm.executeQuery();

                while(rs.next()){
                    user=new User();
                    System.out.println("!!!!!!!!");


                    user.setName(rs.getString("name"));
                    user.setRoles_id(rs.getInt("roles_id"));

                    user.setEmail(rs.getString("email"));

                    user.setPassportNumber(rs.getString("passportNumber"));

                    user.setId(rs.getInt("id"));
                    user.setPassword(rs.getString("userPassword"));


                }
                System.out.println("dao -"+user);
                return user;


            } catch (SQLException e) {
                e.printStackTrace();
                throw new DAOException();
            }
            finally {
                connect.closeConnection(con,pstm,rs);
            }

        }

        @Override
        public void addUser(User user) throws DAOException, SQLException {

        }

        @Override
        public void deleteUserById(int id) throws DAOException {

            Connection con=null;
            PreparedStatement pstm = null;
            try {
                con=connect.takeConnection();
                pstm=con.prepareStatement(REQUEST_FOR_DELETE_BY_ID);
                pstm.setInt(1,id);
                pstm.executeUpdate();


            } catch (SQLException e) {
                e.printStackTrace();
                throw new DAOException();
            }
            finally {
                connect.closeConnection(con,pstm);

            }
        }

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

                while (rs.next()) {
                    User user=new User();
                    user.setName(rs.getString("name"));

                    user.setEmail(rs.getString("email"));

                    user.setPassportNumber(rs.getString("passportNumber"));

                    user.setRoles_id(rs.getInt("roles_id"));

                    user.setId(rs.getInt("id"));


                    allUsers.add(user);
                    user = null;

                }


            } catch (SQLException e) {
                e.printStackTrace();
                throw new DAOException();
            }
            finally {
                connect.closeConnection(con,pstm,rs);


            }
            return allUsers;
        }

    }

