package DB.DBConnect;

import java.sql.Connection;

/**
 * Created by taoyali on 2018/1/26.
 */
public abstract class DBConnect {
    static private Connection dbConnection = null;

    public Connection getDbConnection() {
        return dbConnection;
    }

    public Connection connectDB() { return null; }

    public void connectSql(String driver, String url, String user, String password) {}

    public void closeSql() {}
}
