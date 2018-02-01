package DB;

import DAO.DAOCallBack;
import DAO.Dealer;
import DAO.ManageUser;
import DAO.UserInfo;
import DB.DBConnect.MysqlDBConnect;
import DB.DBConnect.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by taoyali on 2017/9/3.
 */
public class DBManageUserOperation implements DBOperation {
    private Connection connection;
    private PreparedStatement pStatement;

    public DBManageUserOperation() {
        super();
        DBConnect dbConnect = new MysqlDBConnect();
        connection = dbConnect.getDbConnection();
    }

//    @Override
//    public List<UserInfo> selectByName(String userName) throws Exception {
//        List<UserInfo> userInfos = new ArrayList<UserInfo>();
//        String sql = "select * from  users where name = ?";
//        this.pStatement = this.connection.prepareStatement(sql);
//        this.pStatement.setString(1, userName);
//        ResultSet rs = this.pStatement.executeQuery();
//        while (rs.next()) {
//            UserInfo userInfo = new UserInfo();
//            userInfo.setId(rs.getInt(1));
//            userInfo.setName(rs.getNString(2));
//            userInfo.setPassword(rs.getNString(3));
//            userInfos.add(userInfo);
//        }
//        return userInfos;
//    }

//    @Override
//    public List<UserInfo> selectByName(String userName, String password) throws Exception {
//        List<UserInfo> userInfos = new ArrayList<UserInfo>();
//        String sql = "select * from users where name = ? and pwd = ?";
//        this.pStatement = this.connection.prepareStatement(sql);
//        this.pStatement.setString(1, userName);
//        this.pStatement.setString(2, password);
//        ResultSet rs = this.pStatement.executeQuery();
//        while (rs.next()) {
//            UserInfo userInfo = new UserInfo();
//            userInfo.setId(rs.getInt(1));
//            userInfo.setName(rs.getNString(2));
//            userInfo.setPassword(rs.getNString(3));
//            userInfos.add(userInfo);
//            break;
//        }
//        return userInfos;
//    }


    public List selectByName(String phone, String password, DAOCallBack callBack) throws Exception {
        String sql = "select * from manager where phone = ? and pwd = ?";
        this.pStatement = this.connection.prepareStatement(sql);
        this.pStatement.setString(1, phone);
        this.pStatement.setString(2, password);
        ResultSet rs = this.pStatement.executeQuery();
        return callBack.callBack(rs);
    }

    public boolean addUser(String phone, String pwd) throws Exception {
        String sql = "insert into manager (phone, pwd) values (?,?)";
        this.pStatement = this.connection.prepareStatement(sql);
        this.pStatement.setString(1, phone);
        this.pStatement.setString(2, pwd);
//        this.pStatement.setString(3,user.getEmail());
//        sql = "insert into users (name, pwd, email) values ( 'zhangqi', 'taoyali', null)";
        return this.pStatement.executeUpdate() > 0;
    }

    public boolean addDealer(Dealer dealer) throws Exception {
        String sql = "insert into company " +
                "(dealerCode, dealerName, director, phone, sampleDate, sampleType, dealerAddress, sampleRemake) " +
                "values (?,?,?,?,?,?,?,?)";
        this.pStatement = this.connection.prepareStatement(sql);
        this.pStatement.setString(1, dealer.dealerCode);
        this.pStatement.setString(2, dealer.dealerName);
        this.pStatement.setString(3, dealer.director);
        this.pStatement.setString(4, dealer.phone);
        java.sql.Date sqlDate = new java.sql.Date(dealer.sampleDate.getTime());
        this.pStatement.setDate(5, sqlDate);
        this.pStatement.setInt(6, dealer.sampleType);
        this.pStatement.setString(7, dealer.dealerAddress);
        this.pStatement.setString(8, dealer.sampleRemake);
//        sql = "insert into users (name, pwd, email) values ( 'zhangqi', 'taoyali', null)";
        return this.pStatement.executeUpdate() > 0;
    }

}
