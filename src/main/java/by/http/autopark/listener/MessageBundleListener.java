package by.http.autopark.listener;

import by.http.autopark.dao.DBResourceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;

@WebListener()
public class MessageBundleListener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {
    Logger logger= LoggerFactory.getLogger(MessageBundleListener.class.getName());


    public void contextInitialized(ServletContextEvent sce) {
        logger.info("ресурс логгер сработал");
        DBResourceManager.getInstance();

    }

    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("ресурс логгер закончил работу");

    }

}
