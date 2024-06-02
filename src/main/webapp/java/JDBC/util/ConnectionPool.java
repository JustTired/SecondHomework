package JDBC.util;


import lombok.experimental.UtilityClass;
import org.postgresql.Driver;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@UtilityClass
public class ConnectionPool {
    private static final String URL_TO_DB_KEY = "db.url";
    private static final String USER_KEY = "db.user";
    private static final String PASSWORD_KEY = "db.password";
    private static final String POOL_SIZE_KEY = "db.pool.size";
    private static final Integer DEFAULT_POOL_SIZE = 10;
    private static BlockingQueue<Connection> pool;
    private static List<Connection> sourceConnections;


    static {
        loadDriver();
        openConnection();
        initConnectionPool();
    }

    private static void loadDriver() {
        try {
            Class.forName(Driver.class.getName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Could not load org.postgresql.Driver", e);
        }
    }

    private static void initConnectionPool() {
        var poolSize = PropertiesUtil.getProperty(POOL_SIZE_KEY);
        var size = poolSize == null ? DEFAULT_POOL_SIZE : Integer.parseInt(poolSize);
        pool = new ArrayBlockingQueue<>(size);
        sourceConnections = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            var connection = openConnection();
            var proxyConnection = (Connection)
                    Proxy.newProxyInstance(ConnectionPool.class.getClassLoader(), new Class[]{Connection.class},
                            (proxy, method, args) -> method.getName().equals("close")
                                    ? pool.add((Connection) proxy)
                                    : method.invoke(connection, args));
            pool.add(proxyConnection);
            sourceConnections.add(connection);
        }
    }

    private static Connection openConnection() {
        try {
            return DriverManager.getConnection(
                    PropertiesUtil.getProperty(URL_TO_DB_KEY),
                    PropertiesUtil.getProperty(USER_KEY),
                    PropertiesUtil.getProperty(PASSWORD_KEY)
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void closePool() {
        for (var element : sourceConnections) {
            try {
                element.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static Connection get() {
        try {
            return pool.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
