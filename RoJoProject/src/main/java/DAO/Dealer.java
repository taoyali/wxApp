package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by taoyali on 2018/2/1.
 * 代理商
 */
public class Dealer implements DAOCallBack {
    // 代理商代码
    public int id;
    // 代理商代码
    public String dealerCode;
    // 代理商名称
    public String dealerName;
    // 代理商负责人
    public String director;
    // 代理商联系号码
    public String phone;
    // 上样时间
    public Date sampleDate;
    // 样品类型
    public int sampleType;
    // 代理商地址
    public String dealerAddress;
    // 备注
    public String sampleRemake;

    public List<Dealer> callBack(ResultSet resultSet) throws Exception {
        List<Dealer> dealers = new ArrayList<Dealer>();
        while (resultSet.next()) {
            Dealer order = new Dealer();
            order.id = resultSet.getInt("id");
            order.dealerCode = resultSet.getNString("dealerCode");
            order.dealerName = resultSet.getString("dealerName");
            order.director = resultSet.getNString("director");
            java.sql.Date sqlDate = resultSet.getDate("sampleDate");
            order.sampleDate = new java.util.Date(sqlDate.getTime());
            order.phone = resultSet.getString("phone");
            order.sampleType = resultSet.getInt("sampleType");
            order.dealerAddress = resultSet.getString("dealerAddress");
            order.sampleRemake = resultSet.getString("sampleRemake");
            dealers.add(order);
        }
        return dealers;
    }
}
