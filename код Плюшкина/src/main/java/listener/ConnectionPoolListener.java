package listener;

import autopark.dao.connectionPool.ConnectionPool;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;

@WebListener
public class ConnectionPoolListener implements ServletContextListener {

    public ConnectionPoolListener() {
    }


    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("listener start work");
        ConnectionPool.getInstance().initPoolData();

    }

    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("listener stop");
    }

}
