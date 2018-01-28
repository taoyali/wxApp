package DB;

import DB.DBConnect.DBConnect;

/**
 * Created by taoyali on 2018/1/26.
 */
public class DBManager {
    private static DBManager dbManager = new DBManager();
    public DBConnect dbConnect;
    public DBManager init(DBConnect dbConnect) {
        dbManager.dbConnect = dbConnect;
        return dbManager;
    }
}
