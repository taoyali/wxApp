package servlet;

import DAO.Order;
import DB.OrderOperation;
import com.alibaba.fastjson.JSONObject;
import util.FromJson;
import util.ResponseJsonUtils;
import util.xmlUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by taoyali on 2018/4/1.
 */
public class WXPayNotifyServlet extends HttpServlet {
    //    private static Logger log = Logger.getLogger(WXPayServlet.class);
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

    private void payNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String jsonString = FromJson.getRequestJsonString(request);
        Map<String, Object> result = xmlUtil.Dom2Map(jsonString);
        String orderId = result.get("attach").toString();
        OrderOperation orderOperation = new OrderOperation();
        Boolean status = false;
        try {
            int statusInt = orderOperation.update(orderId);
            ResponseJsonUtils.json(response, statusInt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void paySucess(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject jsonObject = FromJson.getJsonObject(request);
        String orderId = jsonObject.getString("id");
        OrderOperation orderOperation = new OrderOperation();
        Boolean status = false;
        try {
            int statusInt = orderOperation.update(orderId);
            ResponseJsonUtils.json(response, statusInt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
