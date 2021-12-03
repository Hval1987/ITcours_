package by.itcourse.autopark.dao.daorealize;

import by.itcourse.autopark.dao.connectionpool.ConnectionPool;

abstract class DAOConnection {

    private final  ConnectionPool connectionpool;
      public DAOConnection()
    {

        connectionpool = ConnectionPool.getInstance();

    }
    public ConnectionPool getConnectionPool()  {
          return connectionpool;
    }

}
