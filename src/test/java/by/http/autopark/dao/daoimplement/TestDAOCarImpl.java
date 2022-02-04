package by.http.autopark.dao.daoimplement;

import by.http.autopark.bean.Car;
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


public class TestDAOCarImpl {
    private static final String SQL_QUERY_BEFORE = "CREATE TABLE park.car (" +
            "id INTEGER NOT NULL auto_increment," +
            "transport_type VARCHAR(45) NOT NULL," +
            "driverId INTEGER NOT NULL," +
            "regNumber VARCHAR(45) NOT NULL," +
            "available VARCHAR(45) NOT NULL," +
            "PRIMARY KEY (id));" +

            "INSERT INTO park.car( transport_type, driverId, regNumber, available) VALUES ( 'bus', 1, 'АК 7494-5', 'SERVISEABLE');";

    private static final String SQL_QUERY_AFTER = "DROP TABLE park.car";

    private static final Logger log = LoggerFactory.getLogger(TestDAOCarImpl.class.getName());

    public DAOCarImpl daoCarForTest;
    public Connection connection = null;
    public Car emptyCar;
    public Car testCar = new Car();
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
            daoCarForTest = new DAOCarImpl();
            testCar = new Car();
            log.warn("connection init");

        } catch (SQLException ex) {
            throw new RuntimeException(ex);

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
            new RuntimeException(e);
        }
        log.warn("connection is killed");

    }

    @Test
    public void test_change_availeability() {
        log.warn("test#1");
        String ability = "FAULTY";
        try {
            daoCarForTest.changeAvailability("FAULTY", 1);
            Assert.assertEquals(daoCarForTest.findCar(1).getAvailable(), "FAULTY");
        } catch (DAOException e) {
            e.printStackTrace();
            throw new AssertionFailedException(e);
        }
    }

    @Test
    public void test_find_car_by_id_where_not_exist() {
        log.warn("test#2");
        int id = 0;
        try {
            Car car = daoCarForTest.findCar(id);
            Assert.assertEquals(daoCarForTest.findCar(id), emptyCar);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new AssertionFailedException(e);
        }
    }
    @Test
    public void testFindTestCarAfterAdd(){
        try {
            daoCarForTest.addCar(testCar);

        Assert.assertEquals(daoCarForTest.findCar(2).getRegNumber(),testCar.getRegNumber());
        } catch (DAOException e) {
            e.printStackTrace();
            throw new AssertionFailedException(e);
        }



    }

    @Test
    public void test_add_car() {
        try {
            daoCarForTest.addCar(testCar);
            int size = daoCarForTest.getAllCars().size();
            Assert.assertEquals(size, 2);

        } catch (DAOException e) {
            e.printStackTrace();
            throw new AssertionFailedException(e);
        }
    }

    @Test
    public void test_get_all_car() {
        try {
            Assert.assertNotEquals(daoCarForTest.getAllCars().size(), 0);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new AssertionFailedException(e);
        }
    }

    @Test
    public void testDeleteCar() {
        try {
            daoCarForTest.deleteCarById(1);

            Assert.assertNotEquals(daoCarForTest.getAllCars().size(), 0);

        } catch (DAOException e) {
            e.printStackTrace();
        }

    }

}







