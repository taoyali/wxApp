package servlet;

import DAO.Dealer;
import DAO.Order;
import DB.DealerOperation;
import DB.OrderOperation;
import com.alibaba.fastjson.JSONObject;
import util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.sym.error;

/**
 * Created by taoyali on 2018/2/1.
 */
public class OrderServlet extends HttpServlet {

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
            int pageIndex = jsonObject.getInteger("pageIndex");
            int pageSize = jsonObject.getInteger("pageSize");
            String phone =  jsonObject.getString("phone");
            String pwd =  jsonObject.getString("pwd");
            OrderOperation orderOperation = new OrderOperation();
            Boolean status = false;
            try {
                List dealers = orderOperation.query(pageIndex, pageSize, phone, pwd, new Order());
                ResponseJsonUtils.json(response, dealers);
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
            int pageIndex = jsonObject.getInteger("pageIndex");
            int pageSize = jsonObject.getInteger("pageSize");
            OrderOperation orderOperation = new OrderOperation();
            String registStatus = new String();
            Boolean status = false;
            try {
                List dealers = orderOperation.query(pageIndex, pageSize, new Order());
                ResponseJsonUtils.json(response, dealers);
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
            Order order = FromJson.StringToEntity(json, Order.class);//json字符串转换成jsonobject对象(request);
            order.installDate = new Date();
            if (order.customName.length() <= 0 || order.productName.length() <= 0
                    || order.phone.length() <= 0 || order.address.length() <= 0
                    || order.doorType < 0) {
                NetStatus error = util.Error.error();
                ResponseJsonUtils.json(response, error);
                return;
            }
            OrderOperation addOrder = new OrderOperation();
            String registStatus = new String();
            Boolean status = false;
            try {
                status = addOrder.add(order);
                if (status) {
                    registStatus = "添加商品成功";
                } else  {
                    registStatus = "增加商品失败";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (status) {
                ResponseJsonUtils.json(response, error);
            }
        } catch (Exception e) {
            System.out.print(e);
            e.printStackTrace();
        }
    }
}
