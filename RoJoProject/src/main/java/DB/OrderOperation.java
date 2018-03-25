package DB;

import DAO.DAOCallBack;
import DAO.Dealer;
import DAO.Order;

import java.sql.ResultSet;
import java.util.Date;
import java.util.List;

/**
 * Created by taoyali on 2018/2/3.
 */
public class OrderOperation extends DBOperationAbstrct {
    public List query(int pageIndex, int pageSize, DAOCallBack callBack) throws Exception {

        String sql = "select *, d.dealerName, d.dealerCode from myorder o inner join dealer d on o.dealer_id = d.id";
        this.pStatement = this.connection.prepareStatement(sql);
        ResultSet rs = this.pStatement.executeQuery();
        return callBack.callBack(rs);
    }

    public List query(int pageIndex, int pageSize, String phone, String pwd, DAOCallBack callBack) throws Exception {

        String sql = "select *, d.dealerName, d.dealerCode from myorder o inner join dealer d on o.dealer_id = d.id and d.phone=? and d.pwd=?";
        this.pStatement = this.connection.prepareStatement(sql);
        this.pStatement.setString(1, phone);
        this.pStatement.setString(2, pwd);
        ResultSet rs = this.pStatement.executeQuery();
        return callBack.callBack(rs);
    }

    public boolean add(Order order) throws Exception {
        String sql = "insert into myorder " +
                "(customName, productName, phone, installDate, doorType, address, remake, scaleboardWidth, scaleboardHeight, count, totalPrice, dealer_id, status, userID)" +
                "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        this.pStatement = this.connection.prepareStatement(sql);
        this.pStatement.setString(1, order.customName);
        this.pStatement.setString(2, order.productName);
        this.pStatement.setString(3, order.phone);
        java.sql.Date sqlDate = new java.sql.Date(order.installDate.getTime());
        this.pStatement.setDate(4, sqlDate);
        this.pStatement.setInt(5, order.doorType);
        this.pStatement.setString(6, order.address);
        this.pStatement.setString(7, order.remake);
        this.pStatement.setFloat(8, order.scaleboardWidth);
        this.pStatement.setFloat(9, order.scaleboardHeight);
        this.pStatement.setInt(10, order.count);
        this.pStatement.setFloat(11, order.totalPrice);
        this.pStatement.setFloat(12, order.dealer_id);
        this.pStatement.setInt(13, order.status);
        this.pStatement.setInt(14, order.userID);
        return this.pStatement.executeUpdate() > 0;
    }
}
