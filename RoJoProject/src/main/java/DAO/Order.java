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
    public String id;
    // 客户名称
    public String customName;
    // 产品名称
    public String productName;
    // 代理商负责人
    public String director;
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

    public List<Order> callBack(ResultSet resultSet) throws Exception {
        List<Order> managerUsers = new ArrayList<Order>();
        while (resultSet.next()) {
            Order order = new Order();
//            manageUser.setId(resultSet.getInt(1));
//            manageUser.setName(resultSet.getNString(1));
//            manageUser.setName(resultSet.getString("name"));
//            manageUser.setPwd(resultSet.getNString(2));
//            manageUser.setPhone(resultSet.getString("phone"));
//            manageUser.setPwd(resultSet.getString("pwd"));
            managerUsers.add(order);
            break;
        }
        return managerUsers;
    }
}
