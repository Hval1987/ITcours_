package by.http.autopark.dao.daoimplement;

import by.http.autopark.bean.User;
import by.http.autopark.dao.DBParameter;
import by.http.autopark.dao.DBResourceManager;
import by.http.autopark.dao.connectionpool.ConnectionPool;
import by.http.autopark.dao.exception.ConnectionPoolException;
import by.http.autopark.dao.exception.DAOException;
import com.mysql.cj.exceptions.AssertionFailedException;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class TestDAOUserImpl {


    private static final Logger log = LoggerFactory.getLogger(TestDAOUserImpl.class.getName());
    private static final String SQL_QUERY_BEFORE="CREATE TABLE park.user (id INTEGER NOT NULL auto_increment," +
            "login VARCHAR(45) NOT NULL," +
            "roles_id INTEGER NOT NULL," +
            "email VARCHAR(45) NOT NULL," +
            "passportNumber VARCHAR(45)," +
            "userPassword VARCHAR(45) NOT NULL," +
            "name VARCHAR(45) NOT NULL," +
            "PRIMARY KEY (id));" +

            "INSERT INTO park.user(id, login, roles_id, email, passportNumber, " +
            "userPassword, name) VALUES (15, 'Valery', 2, " +
            "'hval2006@ya.ru', 'MC 1233333', '0', 'Valera');";
    private static final String SQL_QUERY_AFTER="DROP TABLE park.user";


    public DAOUserImpl daoUserForTest;
    public Connection connection = null;
    public User emptyUser;
    public User testUser=new User();
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
        ConnectionPool connect=ConnectionPool.getInstance();

        try {

            con=connect.takeConnection();
            st=con.createStatement();
            st.execute(SQL_QUERY_BEFORE);
            daoUserForTest = new DAOUserImpl();

            log.warn("connection init");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        emptyUser=new User();

        testUser=new User();
        testUser.setName("Test");
        testUser.setPassportNumber("MC5673452");
        testUser.setEmail("test@user.by");
        testUser.setRoles_id(3);
        testUser.setPassword("123456789");
        testUser.setLogin("MyTest");

    }
    @After
    public void killConnect(){

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
    public void test_find_user_by_name_where_name_is_null() {
        log.warn("test#1");
        try {

            Assert.assertEquals(daoUserForTest.findUserByName(""),emptyUser);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new AssertionFailedException(e);
        }

    }
    @Test
    public void test_find_user_by_name_where_name_not_valid() {
        log.warn("test#2");
        try {
            daoUserForTest.REQUEST_FOR_SEARCH_BY_NAME="SELECT * FROM park.user WHERE name=?";
            String name="John";
            Assert.assertEquals(daoUserForTest.findUserByName(name),emptyUser);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new AssertionFailedException(e);
        }
    }
    @Test
    public void test_registration_and_delete_user(){
        log.warn("test#3");
        try {
            daoUserForTest.registration(testUser);
            User user=daoUserForTest.findUserByLogin(testUser.getLogin());
            Assert.assertNotNull(daoUserForTest.findUserByLogin(testUser.getLogin()));
            int id=user.getId();
            daoUserForTest.deleteUserById(id);
            Assert.assertEquals(daoUserForTest.findUserByLogin(testUser.getLogin()),emptyUser);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new AssertionFailedException(e);
        }
    }

    @Test
    public void test_find_user_by_id_where_id_is_null() {
        log.warn("test#4");
        try {
            int id=0;
            Assert.assertEquals(daoUserForTest.findUser(id),emptyUser);
        } catch (DAOException  e) {
            e.printStackTrace();
            throw new AssertionFailedException(e);
        }
    }
    @Test
    public void test_get_users_by_role_where_role_incorrect(){
        log.warn("test#5");
        int role=5;
        try {
            Assert.assertEquals(daoUserForTest.getUsersByRole(5).size(),0);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new AssertionFailedException(e);
        }
    }
    @Test
    public void TestAddUser(){
        log.warn("test#6");
        try{
            daoUserForTest.registration(testUser);
            User tmp=daoUserForTest.findUserByLogin(testUser.getLogin());
            Assert.assertEquals(tmp.getLogin(),testUser.getLogin());
        }
        catch (DAOException exc){
            throw new AssertionFailedException(exc);
        }
    }

}



