package DAO;

import java.sql.ResultSet;
import java.util.List;

/**
 * Created by taoyali on 2018/1/26.
 */
public interface DAOCallBack<T> {
    public List<T> callBack(ResultSet resultSet) throws Exception;
}
