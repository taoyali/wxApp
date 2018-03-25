package servlet;

import DAO.Dealer;
import DB.DealerOperation;
import com.alibaba.fastjson.JSONObject;
import util.*;
import util.Error;

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
public class DealerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String name = request.getParameter("authenticationCode");
        // 获取方法名字
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

    private void delete(HttpServletRequest request, HttpServletResponse response) {
        // TODO Auto-generated method stub
        System.out.println("delete");

    }

    private void query(HttpServletRequest request, HttpServletResponse response) {
        // TODO Auto-generated method stub
        try {
            JSONObject jsonObject = FromJson.getJsonObject(request);
            int pageIndex = jsonObject.getInteger("pageIndex");
            int pageSize = jsonObject.getInteger("pageSize");
            String phone =  jsonObject.getString("userName");
            String pwd =  jsonObject.getString("userPwd");

            DealerOperation queryDealer = new DealerOperation();
            Boolean status = false;
            try {
                List dealers = queryDealer.query(pageIndex, pageSize, phone, pwd, new Dealer());
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
            String phone =  jsonObject.getString("phone");
            String pwd =  jsonObject.getString("pwd");

            DealerOperation queryDealer = new DealerOperation();
            Boolean status = false;
            try {
                List dealers = queryDealer.query(pageIndex, pageSize, new Dealer());
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
            Dealer dealer = FromJson.StringToEntity(json, Dealer.class);//json字符串转换成jsonobject对象(request);
            System.out.print(dealer);
            dealer.sampleDate = new Date();
            if (dealer.dealerName.length() <= 0 || dealer.dealerCode.length() <= 0
                    || dealer.director.length() <= 0 || dealer.phone.length() <= 0
                    || dealer.dealerAddress.length() <= 0 || dealer.sampleRemake.length() <= 0
                    || dealer.sampleType < 0) {

                NetStatus error = Error.error();
                ResponseJsonUtils.json(response, error);
                return;
            }
            DealerOperation addDealer = new DealerOperation();
            String registStatus = new String();
            Boolean status = false;
            try {
                status = addDealer.add(dealer);
                if (status) {
                    registStatus = "增加代理商成功";
                } else  {
                    registStatus = "增加代理商失败";
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
