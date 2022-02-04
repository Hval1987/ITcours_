package by.http.autopark.dao;

import java.util.ResourceBundle;
public class DBResourceManager {
    private final static DBResourceManager instance = new DBResourceManager();

    private DBResourceManager() {
    }
/*
Name DB for work-"db"
Name DB for test-"dbtest"

 */
    private  ResourceBundle bundle=ResourceBundle.getBundle("db");
    private  ResourceBundle bundletest=ResourceBundle.getBundle("dbtest");
    private  ResourceBundle messageBundle=ResourceBundle.getBundle("message");


    public static DBResourceManager getInstance() {
        return instance;
    }


    public String getValue(String key){
        return bundle.getString(key);
    }
    public String getMessageValue(String key){return messageBundle.getString(key);
    }

}

