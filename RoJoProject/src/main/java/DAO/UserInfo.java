package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by taoyali on 2017/9/3.
 */
 public class UserInfo implements DAOCallBack {
    private Integer id;
    private String name;
    private String password;
    private String email;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) { this.name = name; }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserInfo() {
         super();
    }

    public UserInfo(String name, String pwd, String email) {
        super();
        this.name = name;
        this.password = pwd;
        this.email = email;
    }

    public List<UserInfo> callBack(ResultSet resultSet) throws Exception {
        List<UserInfo> userInfos = new ArrayList<UserInfo>();
        while (resultSet.next()) {
            UserInfo userInfo = new UserInfo();
            userInfo.setId(resultSet.getInt(1));
            userInfo.setName(resultSet.getString(2));
            userInfo.setPassword(resultSet.getString(3));
            userInfos.add(userInfo);
        }
        return userInfos;
    }
}
