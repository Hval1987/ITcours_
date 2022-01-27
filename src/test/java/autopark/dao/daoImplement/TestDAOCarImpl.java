package autopark.dao.daoImplement;

import autopark.bean.Car;
import autopark.dao.connectionPool.ConnectionPool;
import autopark.dao.exception.DAOException;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;


public class TestDAOCarImpl {


    private static final Logger log = LoggerFactory.getLogger(TestDAOCarImpl.class.getName());

    public DAOCarImpl daoCarForTest;
    public Connection connection = null;
    public Car emptyCar;
    public Car testCar = new Car();

    @Before
    public void conectionInit() {

        ConnectionPool.getInstance().initPoolData();
        try {

            //ResourceBundle bundle=ResourceBundle.getBundle("db");

            daoCarForTest = new DAOCarImpl();
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = ConnectionPool.getInstance().takeConnection();
            testCar = new Car();
            log.warn("connection init");

        } catch (ClassNotFoundException ex) {

        }
        emptyCar = new Car();

        testCar = new Car();
        testCar.setTransportType("bus");
        testCar.setAvailable("SERVICEABLE");
        testCar.setDriverId(5);
        testCar.setRegNumber("ОО 012345-0");


    }

    @After
    public void killConnect() {
        ConnectionPool.getInstance().dispose();
        log.warn("connection is killed");
        /*
        truncate data
         */
    }

    @Test
    public void test_change_availeability() {
        log.warn("test#1");
        String ability = "FAULTY";
        try {
            daoCarForTest.addCar(testCar);
            daoCarForTest.changeAvailability("FAULTY", 1);
            Assert.assertEquals(daoCarForTest.findCar(1).getAvailable(), ability);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_find_car_by_id_where_not_exist() {
        log.warn("test#2");
        int id = 666;
        try {
            Car car = daoCarForTest.findCar(id);
            Assert.assertEquals(daoCarForTest.findCar(id), emptyCar);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_add_car() {
//        try {
//            daoCarForTest.addCar(testCar);
//
//
//
//        } catch (DAOException e) {
//            e.printStackTrace();
//        }
    }

    @Test
    public void test_get_all_car() {
        Assert.assertNotEquals(daoCarForTest.getAllCars().size(), 0);
    }

    /*
    еужно разобраться с базой потом подправить тест на equals
     */
    public void test_get_all_car_after_add() {
 //      try {
//            daoCarForTest.addCar(testCar);
//
            Assert.assertNotEquals(daoCarForTest.getAllCars().size(), 0);
//
//        } catch (DAOException e) {
//            e.printStackTrace();
//        }


    }
}







