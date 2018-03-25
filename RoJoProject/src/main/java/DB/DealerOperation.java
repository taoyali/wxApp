package DB;

import DAO.DAOCallBack;
import DAO.Dealer;
import DB.DBConnect.DBConnect;
import DB.DBConnect.MysqlDBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by taoyali on 2018/2/3.
 */
public class DealerOperation extends DBOperationAbstrct {

    public List query(String...args) throws Exception {

            return new ArrayList();
    }

    public List query(int pageIndex, int pageSize, DAOCallBack callBack) throws Exception {
        String sql = "select * from dealer";
        this.pStatement = this.connection.prepareStatement(sql);
        ResultSet rs = this.pStatement.executeQuery();
        return callBack.callBack(rs);
    }

    public List query(int pageIndex, int pageSize, String phone, String pwd, DAOCallBack callBack) throws Exception {
        String sql = "select * from dealer where phone=? and pwd=?";
        this.pStatement = this.connection.prepareStatement(sql);
        this.pStatement.setString(1, phone);
        this.pStatement.setString(2, pwd);
        ResultSet rs = this.pStatement.executeQuery();
        return callBack.callBack(rs);
    }

    public List query(String phone, String pwd, DAOCallBack callBack) throws Exception {
        String sql = "select * from dealer where phone=? and pwd=?";
        this.pStatement = this.connection.prepareStatement(sql);
        this.pStatement.setString(1, phone);
        this.pStatement.setString(2, pwd);
        ResultSet rs = this.pStatement.executeQuery();
        return callBack.callBack(rs);
    }

    public boolean add(Dealer dealer) throws Exception {
        String sql = "insert into dealer " +
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
        return this.pStatement.executeUpdate() > 0;
    }
}
