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
    public String id;
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
//            manageUser.setId(resultSet.getInt(1));
//            manageUser.setName(resultSet.getNString(1));
//            manageUser.setName(resultSet.getString("name"));
//            manageUser.setPwd(resultSet.getNString(2));
//            manageUser.setPhone(resultSet.getString("phone"));
//            manageUser.setPwd(resultSet.getString("pwd"));
            dealers.add(order);
            break;
        }
        return dealers;
    }
}
