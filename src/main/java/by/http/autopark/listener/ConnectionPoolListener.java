package by.http.autopark.listener;

import by.http.autopark.dao.connectionpool.ConnectionPool;
import by.http.autopark.dao.exception.ConnectionPoolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ConnectionPoolListener implements ServletContextListener {
    private static final Logger logger= LoggerFactory.getLogger(ConnectionPoolListener.class.getName());

    public ConnectionPoolListener() {
    }


    public void contextInitialized(ServletContextEvent sce) {
        logger.debug("слушатель сработал");
        ConnectionPool.getInstance().initPoolData();

    }

    public void contextDestroyed(ServletContextEvent sce) {

        logger.debug("слушатель завершил работу");

        try {
            ConnectionPool.getInstance().dispose();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
