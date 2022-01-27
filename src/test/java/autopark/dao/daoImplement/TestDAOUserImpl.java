package autopark.dao.daoImplement;

import autopark.bean.User;
import autopark.dao.connectionPool.ConnectionPool;
import autopark.dao.exception.DAOException;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;


public class TestDAOUserImpl {


    private static final Logger log = LoggerFactory.getLogger(TestDAOUserImpl.class.getName());

    public DAOUserImpl daoUserForTest;
    public Connection connection = null;
    public User emptyUser;
    public User testUser=new User();

    @Before
    public void conectionInit() {

        ConnectionPool.getInstance().initPoolData();
        try {

            //ResourceBundle bundle=ResourceBundle.getBundle("db");

            daoUserForTest = new DAOUserImpl();
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = ConnectionPool.getInstance().takeConnection();
            log.warn("connection init");

        } catch (ClassNotFoundException  ex) {

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
        ConnectionPool.getInstance().dispose();
        log.warn("connection is killed");
        /*
        truncate data
         */
    }

    @Test
    public void test_find_user_by_name_where_name_is_null() {
        log.warn("test#1");
        try {

            Assert.assertEquals(daoUserForTest.findUserByName(""),emptyUser);
        } catch (DAOException  e) {
            e.printStackTrace();
        }

    }
    @Test
    public void test_find_user_by_name_where_name_not_valid() {
        log.warn("test#2");
        try {
            String name="Valeraxxx";
            Assert.assertEquals(daoUserForTest.findUserByName(name),emptyUser);
        } catch (DAOException e) {
            e.printStackTrace();
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
        }
    }

}



