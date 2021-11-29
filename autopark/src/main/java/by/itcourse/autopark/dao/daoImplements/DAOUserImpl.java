package by.itcourse.autopark.dao.daoImplements;


import by.itcourse.autopark.bean.User;
import by.itcourse.autopark.dao.DAOUser;
import by.itcourse.autopark.dao.connectionPool.ConnectionPool;
import by.itcourse.autopark.dao.exceptions.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOUserImpl implements DAOUser {

    final ConnectionPool connect=ConnectionPool.getInstance();
    @Override
    public User findUser(int id) throws SQLException {
        Connection con=connect.takeConnection();
        User user = new User();
        try {

            Statement stm=con.createStatement();

            ResultSet rs=stm.getResultSet();
            String query="SELECT * FROM park.user WHERE id="+id;
            rs=stm.executeQuery(query);

            while(rs.next()){
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassportNumber(rs.getString("passportNumber"));
                user.setRoles_id(rs.getInt("roles_id"));
                user.setId(rs.getInt("id"));

            }
            connect.closeConnection(con,stm,rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;

    }

    @Override
    public void addUser(User user) throws DAOException, SQLException {
        Connection con=connect.takeConnection();

        try {

            con.setAutoCommit(false);
            String query="INSERT INTO `park`.`user` (`name`, `roles_id`, `email`,`passportNumber`) VALUES(?,?,?,?)";
            PreparedStatement pstm=con.prepareStatement(query);

            pstm.setString(1,user.getName());
            pstm.setInt(2,user.getRoles_id());
            pstm.setString(3,user.getEmail());
            pstm.setString(4,user.getPassportNumber());
            pstm.executeUpdate();

            con.commit();

           connect.closeConnection(con,pstm);

        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    @Override
    public void deleteUserById(int id) throws SQLException {
        Connection con=connect.takeConnection();
        try {


        String query="DELETE  FROM `park`.`user` WHERE id=?";
        PreparedStatement pstm=con.prepareStatement(query);
        pstm.setInt(1,id);
        pstm.executeUpdate();

        connect.closeConnection(con,pstm);
        } catch (SQLException e) {
        e.printStackTrace();
        }
    }


    @Override
    public List<User> getUsers() throws SQLException {
        List<User> allUsers=new ArrayList<>();
        Connection con=connect.takeConnection();
        try{

            Statement stm=con.createStatement();

            ResultSet rs=stm.getResultSet();
            String request="SELECT * FROM park.order ";
            rs=stm.executeQuery(request);
            User user=new User();
            while(rs.next()) {
                while (rs.next()) {
                    user.setName(rs.getString("name"));
                    user.setEmail(rs.getString("email"));
                    user.setPassportNumber(rs.getString("passportNumber"));
                    user.setRoles_id(rs.getInt("roles_id"));
                    user.setId(rs.getInt("id"));
                    allUsers.add(user);
                    user = null;

                }
            }
            connect.closeConnection(con,stm,rs);

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return allUsers;
    }

}
