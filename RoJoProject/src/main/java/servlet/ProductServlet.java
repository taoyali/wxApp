package servlet;

import DAO.Order;
import DAO.Product;
import DB.OrderOperation;
import DB.ProductOperation;
import com.alibaba.fastjson.JSONObject;
import util.FromJson;
import util.NetStatus;
import util.ResponseJsonUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.sym.error;

/**
 * Created by taoyali on 2018/3/26.
 */
public class ProductServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath();// /xxxxxx.do
        String[] strings = servletPath.split("\\.");
        String methodName = strings[0].replace("/", "");
        try {
            // 利用反射获取方法
            Method method = getClass().getDeclaredMethod(methodName,
                    HttpServletRequest.class, HttpServletResponse.class);
            // 执行相应的方法
            method.invoke(this, request, response);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    private void query(HttpServletRequest request, HttpServletResponse response) {
        // TODO Auto-generated method stub
        try {
            JSONObject jsonObject = FromJson.getJsonObject(request);
            String productName =  jsonObject.getString("productName");
            ProductOperation productOperation = new ProductOperation();
            Boolean status = false;
            try {
                List products = productOperation.query(productName, new Product());
                ResponseJsonUtils.json(response, products);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.print(e);
            e.printStackTrace();
        }
    }

    private void queryList(HttpServletRequest request, HttpServletResponse response) {
        // TODO Auto-generated method stub
        try {
            JSONObject jsonObject = FromJson.getJsonObject(request);
            ProductOperation productOperation = new ProductOperation();
            Boolean status = false;
            try {
                List products = productOperation.queryList(new Product());
                ResponseJsonUtils.json(response, products);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.print(e);
            e.printStackTrace();
        }
    }

    private void add(HttpServletRequest request, HttpServletResponse response) {
        // TODO Auto-generated method stub
        try {
            String json = FromJson.getRequestJsonString(request);
            Product product = FromJson.StringToEntity(json, Product.class);//json字符串转换成jsonobject对象(request);
            if (product.productName.length() <= 0 || product.price <= 0.0) {
                NetStatus error = util.Error.error();
                ResponseJsonUtils.json(response, error);
                return;
            }
            ProductOperation productOperation = new ProductOperation();
            String registStatus = new String();
            Boolean status = false;
            try {
                status = productOperation.add(product);
                if (status) {
                    registStatus = "添加商品成功";
                } else  {
                    registStatus = "增加商品失败";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (status) {
                LinkedHashMap<String, Object> data = new LinkedHashMap<String, Object>();
                data.put("status", status);
                ResponseJsonUtils.json(response, data);
            }
        } catch (Exception e) {
            System.out.print(e);
            e.printStackTrace();
        }
    }
}
