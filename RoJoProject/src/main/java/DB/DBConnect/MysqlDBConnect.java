package DB.DBConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by taoyali on 2018/1/26.
 */
public class MysqlDBConnect extends DBConnect {
    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/rojo";
    private static String user = "root";
    private static String password = "taoyali";
    static private Connection dbConnection = null;

    public Connection getDbConnection() {
        if (dbConnection == null) {
            dbConnection = connectDB();
        }
        return dbConnection;
    }

    public Connection connectDB() {
        try {
            //加载驱动程序
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, user, password);
            if (!connection.isClosed()) {
                System.out.println(" Succeeded connecting to the Database! ");
            }
            return connection;
        } catch (ClassNotFoundException e) {
            //数据库驱动类异常处理
            System.out.println("Sorry,can`t find the Driver!");
            e.printStackTrace();
        } catch (SQLException e) {
            //数据库连接失败异常处理
            e.printStackTrace();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return null;
    }

//    public void connectSql(String driver, String url, String user, String password) {
//        Connection connection;
//        String conDriver = driver != null ? driver : "com.sql.jdbc.Driver";
//        String conUrl = url != null ? url : "jdbc:mysql://localhost:3306/rojo";
//        String conUser = user != null ? user : "root";
//        String conPassword = password != null ? password : "";
//        try {
//            //加载驱动程序
//            Class.forName(conDriver);
//            connection = DriverManager.getConnection(conUrl, conUser, conPassword);
//            if (!connection.isClosed()) {
//                System.out.println(" Succeeded connecting to the Database! ");
//                return;
//            }
//            dbConnection = connection;
//        } catch (ClassNotFoundException e) {
//            //数据库驱动类异常处理
//            System.out.println("Sorry,can`t find the Driver!");
//            e.printStackTrace();
//        } catch (SQLException e) {
//            //数据库连接失败异常处理
//            e.printStackTrace();
//        } catch (Exception e) {
//            // TODO: handle exception
//            e.printStackTrace();
//        }
//    }

    public void closeSql() {

        try {
            if (!dbConnection.isClosed()) {
                dbConnection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
