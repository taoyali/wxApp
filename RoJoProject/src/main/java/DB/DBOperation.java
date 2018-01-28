package DB;

import DAO.DAOCallBack;
import DAO.UserInfo;
import java.util.*;
/**
 * Created by taoyali on 2017/9/3.
 */
public interface DBOperation {
//    public List<UserInfo> selectByName(String userName) throws Exception;
//    public List<UserInfo> selectByName(String userName, String password) throws Exception;
    public List selectByName(String userName, String password, DAOCallBack callBack) throws Exception;
//    public boolean addUser(UserInfo user) throws Exception;
    public boolean addUser(String phone, String pwd) throws Exception;
}
