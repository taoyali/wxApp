package DB;

import DAO.DAOCallBack;
import DAO.Product;

import java.sql.ResultSet;
import java.util.Date;
import java.util.List;

/**
 * Created by taoyali on 2018/3/25.
 */
public class ProductOperation extends DBOperationAbstrct {

    public List query(String productName, DAOCallBack callBack) throws Exception {

        String sql = "select * from product where productName=?";
        this.pStatement = this.connection.prepareStatement(sql);
        this.pStatement.setString(1, productName);
        ResultSet rs = this.pStatement.executeQuery();
        return callBack.callBack(rs);
    }

    public List queryList(DAOCallBack callBack) throws Exception {
        String sql = "select * from product";
        this.pStatement = this.connection.prepareStatement(sql);
        ResultSet rs = this.pStatement.executeQuery();
        return callBack.callBack(rs);
    }

    public List delete(String productName, DAOCallBack callBack) throws Exception {

        String sql = "delete from product where productName=?";
        this.pStatement = this.connection.prepareStatement(sql);
        this.pStatement.setString(1, productName);
        ResultSet rs = this.pStatement.executeQuery();
        return callBack.callBack(rs);
    }

    public boolean add(Product product) throws Exception {
        String sql = "insert into product " +
                "(productName, type, scaleboardWidth, scaleboardHeight, remake, price, count)" +
                "values (?,?,?,?,?,?,?)";
        this.pStatement = this.connection.prepareStatement(sql);
        this.pStatement.setString(1, product.productName);
        this.pStatement.setInt(2, product.type);
        this.pStatement.setFloat(3, product.scaleboardWidth);
        this.pStatement.setFloat(4, product.scaleboardHeight);
        this.pStatement.setString(5, product.remake);
        this.pStatement.setFloat(6, product.price);
        this.pStatement.setInt(7, product.count);

        return this.pStatement.executeUpdate() > 0;
    }
}
