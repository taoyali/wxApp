package DB;

import DAO.DAOCallBack;
import DB.DBConnect.MysqlDBConnect;
import DB.DBConnect.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * Created by taoyali on 2017/9/3.
 */
public class ManageUserOperation extends DBOperationAbstrct implements DBOperationInterface {

    public List query(String...args) throws Exception {
        return null;
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

}
