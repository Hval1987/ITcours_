package by.http.autopark.dao.daoimplement;

import by.http.autopark.bean.ApprovedOrder;
import by.http.autopark.dao.DAOApprovedOrder;
import by.http.autopark.dao.DBParameter;
import by.http.autopark.dao.DBResourceManager;
import by.http.autopark.dao.connectionpool.ConnectionPool;
import by.http.autopark.dao.exception.ConnectionPoolException;
import by.http.autopark.dao.exception.DAOException;
import com.mysql.cj.exceptions.AssertionFailedException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDAOApproverOrdImpl {
    private static final String SQL_QUERY_BEFORE ="CREATE TABLE park.approved_order (" +
            "id INTEGER NOT NULL auto_increment," +
            "id_assigned_car INTEGER NOT NULL," +
            "status VARCHAR(45) NOT NULL," +
            "id_manager INTEGER NOT NULL," +
            "id_driver INTEGER NOT NULL," +
            "id_employer VARCHAR(45) NOT NULL," +
            "id_order INTEGER NOT NULL," +
            "date VARCHAR(45)," +
            "PRIMARY KEY (id)); " +
            "INSERT INTO park.approved_order(id, id_assigned_car, " +
            "status, id_manager, id_driver, id_employer, id_order, date)" +
            " VALUES (1, 1, 'COMPLITED', 6, 15, '5', 1, '23.5.2021');";

    private static final String SQL_QUERY_AFTER = "DROP TABLE park.APPROVED_ORDER";

    private static final Logger log = LoggerFactory.getLogger(TestDAOApproverOrdImpl.class.getName());

    public DAOApprovedOrder daoApprovedOrdForTest;
    public Connection connection = null;
    public ApprovedOrder emptyAppOrd;
    public ApprovedOrder testAppOrd = new ApprovedOrder();
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
            daoApprovedOrdForTest = new DAOApproveOrder();
            testAppOrd = new ApprovedOrder();
            log.warn("connection init");

        } catch (SQLException ex) {
            throw new RuntimeException(ex);

        }
        emptyAppOrd= new ApprovedOrder();

        testAppOrd = new ApprovedOrder();
        testAppOrd.setIdOrder(1);
        testAppOrd.setIdAssignedCar(1);
        testAppOrd.setIdOrder(1);
        testAppOrd.setIdEmployer(5);
        testAppOrd.setStatus("IN_PROCESS");
        testAppOrd.setDate("1.01.2001");
        testAppOrd.setIdManager(6);

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
    public void testAddAppOrder(){
        int size;
        try {
            daoApprovedOrdForTest.addApprovedOrder(testAppOrd);
            size=daoApprovedOrdForTest.getAllApprovedOrders().size();
        } catch (DAOException e) {
            e.printStackTrace();
            throw new AssertionFailedException(e);
        }
        Assert.assertEquals(size,2);
    }

    @Test
    public void testFindAppOrderByTrueId(){
        int id=1;
        ApprovedOrder test;
        try {
            test=daoApprovedOrdForTest.findApprovedOrder(id);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new AssertionFailedException(e);
        }
        Assert.assertEquals(test.getId(),id);
    }

    @Test
    public void testFindAppOrderByWrongId(){
        int id=1;
        ApprovedOrder test;
        try {
            id=daoApprovedOrdForTest.getAllApprovedOrders().size()+1;
            test=daoApprovedOrdForTest.findApprovedOrder(id);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new AssertionFailedException(e);
        }
        Assert.assertEquals(test.getId(),0);
    }

    @Test
    public void testFindAppOrderByTrueCar(){

        int idCar=testAppOrd.getIdAssignedCar();
        int sizeBefore;
        int sizeAfter;
        try {
            sizeBefore=daoApprovedOrdForTest.findApproveOrderByCar(idCar).size();
            daoApprovedOrdForTest.addApprovedOrder(testAppOrd);
            sizeAfter=daoApprovedOrdForTest.findApproveOrderByCar(idCar).size();
            Assert.assertTrue(sizeAfter-sizeBefore==1);

        } catch (DAOException e) {
            e.printStackTrace();
            throw new AssertionFailedException(e);
        }

    }
    @Test
    public void testDeleteAppOrder(){
        int sizeBefore;
        int sizeAfter;
        try {
            sizeBefore=daoApprovedOrdForTest.getAllApprovedOrders().size();
            daoApprovedOrdForTest.deleteApprovedOrderById(1);
            sizeAfter=daoApprovedOrdForTest.getAllApprovedOrders().size();
            Assert.assertTrue(sizeBefore-sizeAfter==1);

        } catch (DAOException e) {
            e.printStackTrace();
            throw new AssertionFailedException(e);
        }


    }



}
