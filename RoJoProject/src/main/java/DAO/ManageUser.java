package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by taoyali on 2018/1/28.
 */
public class ManageUser implements DAOCallBack {
    private Integer id;
    private String name;
    private String pwd;
    private String phone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<ManageUser> callBack(ResultSet resultSet) throws Exception {
        List<ManageUser> managerUsers = new ArrayList<ManageUser>();
        while (resultSet.next()) {
            ManageUser manageUser = new ManageUser();
//            manageUser.setId(resultSet.getInt(1));
//            manageUser.setName(resultSet.getNString(1));
            manageUser.setName(resultSet.getString("name"));
//            manageUser.setPwd(resultSet.getNString(2));
            manageUser.setPhone(resultSet.getString("phone"));
            manageUser.setPwd(resultSet.getString("pwd"));
            managerUsers.add(manageUser);
            break;
        }
        return managerUsers;
    }
}
