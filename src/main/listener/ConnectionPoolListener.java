package listener;

import autopark.dao.connectionPool.ConnectionPool;
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

        ConnectionPool.getInstance().dispose();
    }

}
