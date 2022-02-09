package by.http.autopark.dao.daoimplement;

import by.http.autopark.bean.Role;
import by.http.autopark.dao.DAORole;
import by.http.autopark.dao.DBParameter;
import by.http.autopark.dao.DBResourceManager;
import by.http.autopark.dao.connectionpool.ConnectionPool;
import by.http.autopark.dao.exception.ConnectionPoolException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDAORoleImpl {
    private static final String SQL_QUERY_BEFORE ="CREATE TABLE PARK.ROLE (id INTEGER NOT NULL auto_increment ," +
            "tittle VARCHAR(45) NOT NULL," +
            "PRIMARY KEY (id));" +
            "INSERT INTO PARK.ROLE(id, tittle) VALUES (1, 'Admin');";


    private static final String SQL_QUERY_AFTER = "DROP TABLE PARK.ROLE";

    private static final Logger log = LoggerFactory.getLogger(TestDAORoleImpl.class.getName());

    public DAORole daoRoleTest;
    public Connection connection = null;
    public Role emptyRole;
    public Role testRole = new Role();
    public Connection con;
    public Statement st;

    @Before

    public void conectionInit() {
        /*
        Проверка на правильность базы,
        чтоб не дропнуть нормальную базу
         */

        DBResourceManager dbmanager=DBResourceManager.getInstance();
        String urlDriver=dbmanager.getValue(DBParameter.DB_DRIVER);
        if(urlDriver.contains("com.mysql.cj.jdbc.Driver")){
            System.err.println("ПОМЕНЯЙ БАЗУ!!!!!");
            System.exit(0);

        }

        ConnectionPool.getInstance().initPoolData();
        ConnectionPool connect = ConnectionPool.getInstance();
        try {
            con = connect.takeConnection();
            st = con.createStatement();
            st.execute(SQL_QUERY_BEFORE);
            daoRoleTest = new DAORoleImpl();
            testRole = new Role();
            log.warn("connection init");

        } catch (SQLException ex) {
            throw new RuntimeException(ex);

        }
        emptyRole= new Role();

        testRole = new Role();
        testRole.setRole("DRIVER");



    }

    @After
    public void killConnect() {

        try {
            st.execute(SQL_QUERY_AFTER);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        try {
            ConnectionPool.getInstance().dispose();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        log.warn("connection is killed");

    }
    @Test
    public void testAddRole(){

    }
}
