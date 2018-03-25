package DB;

import DB.DBConnect.DBConnect;
import DB.DBConnect.MysqlDBConnect;

import java.sql.PreparedStatement;
import java.sql.Connection;

/**
 * Created by taoyali on 2018/2/3.
 */
abstract public class DBOperationAbstrct {

    public Connection connection;
    public PreparedStatement pStatement;

    public  DBOperationAbstrct () {
        super();
        DBConnect dbConnect = new MysqlDBConnect();
        connection = dbConnect.getDbConnection();
    }
}
