package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by taoyali on 2018/2/1.
 */
public class Order implements DAOCallBack {
    // 订单id
    public int id;
    // 客户名称
    public String customName;
    // 产品名称
    public String productName;
    // 客户联系号码
    public String phone;
    // 安装时间
    public Date installDate;
    // 门类型
    public int doorType;
    // 客户地址
    public String address;
    // 备注
    public String remake;
    // 衬板宽度
    public float scaleboardWidth;
    // 衬板高度
    public float scaleboardHeight;
    // 商品个数
    public int count;
    // 单价
    public float totalPrice;
    // 外键商品
    public int dealer_id;

    public String dealerName;
    public String dealerCode;
    public int status;
    public int userID;

    public List<Order> callBack(ResultSet resultSet) throws Exception {
        List<Order> orders = new ArrayList<Order>();
        while (resultSet.next()) {
            Order order = new Order();
            order.id = resultSet.getInt("id");
            order.customName = resultSet.getString("customName");
            order.productName = resultSet.getString("productName");
            order.phone = resultSet.getString("phone");
            order.installDate = resultSet.getDate("installDate");
            order.status = resultSet.getInt("status");
            order.doorType = resultSet.getInt("doorType");
            order.address = resultSet.getString("address");
            order.remake = resultSet.getString("remake");
            order.scaleboardWidth = resultSet.getFloat("scaleboardWidth");
            order.scaleboardHeight = resultSet.getFloat("scaleboardHeight");
            order.count = resultSet.getInt("count");
            order.dealer_id = resultSet.getInt("dealer_id");
            order.totalPrice = resultSet.getFloat("totalPrice");
            order.dealerName = resultSet.getString("dealerName");
            order.dealerCode = resultSet.getString("dealerCode");
            order.userID = resultSet.getInt("userID");
            orders.add(order);
        }
        return orders;
    }
}
