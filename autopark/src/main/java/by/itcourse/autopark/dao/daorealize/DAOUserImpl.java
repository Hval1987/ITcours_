package by.itcourse.autopark.dao.daorealize;



import by.itcourse.autopark.bean.User;
import by.itcourse.autopark.dao.DAOUser;
import by.itcourse.autopark.dao.connectionpool.ConnectionPool;
import by.itcourse.autopark.dao.exception.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOUserImpl extends DAOConnection implements DAOUser {
    private static final String  REQUEST_FOR_SEARCH="SELECT * FROM park.user WHERE id=?";
    private static final String  REQUEST_FOR_INSERT="INSERT INTO `park`.`user` " +
            "(`id`, `name`,`roles_id`,`email`,`passportNumber`) VALUES(?,?,?,?)";
    private static final String REQUEST_FOR_DELETE_BY_ID="DELETE  FROM `park`.`user` WHERE id=?";
    private static final String REQUEST_FOR_SELECT_ALL_CARS="SELECT * FROM park.user ";

    static final ConnectionPool connect=ConnectionPool.getInstance();

    @Override
    public User findUser(int id) throws DAOException {
        Connection con=null;
        PreparedStatement pstm=null;
        ResultSet rs=null;
        User user ;

        try {
            con=getConnectionPool().takeConnection();

            pstm=con.prepareStatement(REQUEST_FOR_SEARCH);
            pstm.setInt(1,id);

            rs=pstm.executeQuery();
            user=new User();
            while(rs.next()){
                user.setName(rs.getString("name"));
                user.setRoles_id(rs.getInt("roles_id"));
                user.setEmail(rs.getString("email"));
                user.setPassportNumber(rs.getString("passsportNumber"));
                user.setId(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException();
        }
        finally {
            connect.closeConnection(con,pstm,rs);
        }
        return user;
    }
    @Override
    public void addUser(User user) throws DAOException {
        Connection con=null;
        PreparedStatement pstm=null;

        try {

            con=connect.takeConnection();

            pstm=con.prepareStatement(REQUEST_FOR_INSERT);

            pstm.setString(1,user.getName());
            pstm.setInt(2,user.getRoles_id());
            pstm.setString(3,user.getEmail());
            pstm.setString(4,user.getPassportNumber());
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
    public List<User> getUsers() throws DAOException {
        List<User> allUsers=new ArrayList<>();
        Connection con=null;
        Statement stm=null;
        ResultSet rs=null;
        try{

            stm=con.createStatement();
            con=connect.takeConnection();
            rs=stm.getResultSet();
            rs=stm.executeQuery(REQUEST_FOR_SELECT_ALL_CARS);
            User user=new User();
            while (rs.next()) {
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
            connect.closeConnection(con,stm,rs);

        }
        return allUsers;
    }

}
