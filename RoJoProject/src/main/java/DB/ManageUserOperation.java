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
public class ManageUserOperation extends DBOperationAbstrct {

    public List query(String...args) throws Exception {
        return null;
    }

    public List selectManager(String phone, String password, DAOCallBack callBack) throws Exception {
        String sql = "select * from manager where phone = ? and pwd = ?";
        this.pStatement = this.connection.prepareStatement(sql);
        this.pStatement.setString(1, phone);
        this.pStatement.setString(2, password);
        ResultSet rs = this.pStatement.executeQuery();
        return callBack.callBack(rs);
    }

    public boolean addManager(String phone, String pwd) throws Exception {
        String sql = "insert into manager (phone, pwd) values (?,?)";
        this.pStatement = this.connection.prepareStatement(sql);
        this.pStatement.setString(1, phone);
        this.pStatement.setString(2, pwd);
        return this.pStatement.executeUpdate() > 0;
    }

    public List selectUser(String phone, String password, DAOCallBack callBack) throws Exception {
        String sql = "select * from user where phone = ? and pwd = ?";
        this.pStatement = this.connection.prepareStatement(sql);
        this.pStatement.setString(1, phone);
        this.pStatement.setString(2, password);
        ResultSet rs = this.pStatement.executeQuery();
        return callBack.callBack(rs);
    }

//    public boolean addUser(String phone, String pwd) throws Exception {
//        String sql = "insert into user (phone, pwd) values (?,?)";
//        this.pStatement = this.connection.prepareStatement(sql);
//        this.pStatement.setString(1, phone);
//        this.pStatement.setString(2, pwd);
////        this.pStatement.setString(3,user.getEmail());
////        sql = "insert into users (name, pwd, email) values ( 'zhangqi', 'taoyali', null)";
//        return this.pStatement.executeUpdate() > 0;
//    }

}
