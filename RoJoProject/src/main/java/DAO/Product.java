package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by taoyali on 2018/3/25.
 */
public class Product implements DAOCallBack {
    public Integer id;
    public String productName;
    public float price;
    public int type;
    public int count;
    public String remake;
    public float scaleboardWidth;
    public float scaleboardHeight;

    public List<Product> callBack(ResultSet resultSet) throws Exception {
        List<Product> products = new ArrayList<Product>();
        while (resultSet.next()) {
            Product product = new Product();
            product.id = resultSet.getInt("id");
            product.productName = resultSet.getString("productName");
            product.type = resultSet.getInt("type");
            product.scaleboardWidth = resultSet.getFloat("scaleboardWidth");
            product.scaleboardHeight = resultSet.getFloat("scaleboardHeight");
            product.remake = resultSet.getString("remake");
            product.price = resultSet.getFloat("price");
            product.count = resultSet.getInt("count");
            products.add(product);
            break;
        }
        return products;
    }
}
