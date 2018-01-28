package servlet;

import DAO.ManageUser;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import util.*;
import DB.DBManageUserOperation;
import util.Error;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by taoyali on 2017/9/3.
 */
@WebServlet(name = "RegistServlet")
public class RegistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        String json = "{\"name\":\"刘德华\",\"age\":35,\"some\":[{\"k1\":\"v1\",\"k2\":\"v2\"},{\"k3\":\"v3\",\"k4\":\"v4\"}]}";

//        String json = "{name:\"刘德华\"}";
        String json = FromJson.getRequestJsonString(request);
        ManageUser manageUser = FromJson.StringToEntity(json, ManageUser.class);//json字符串转换成jsonobject对象


//        String jsonString = FromJson.getRequestJsonString(request);
//        JSONObject jsonObject = JSONObject.parseObject(jsonString);
//        String phone = jsonObject.getString("phone");

//        if (!CheckPhone.isPhoneLegal(phone)) {
//            // java对象转json字符串
////            String jsonString = JSON.toJSONString(group);
//
//            TYLError error = new TYLError();
//            error.errorCode = "1234";
//            error.errorInfo = "参数错误";
//            error.errorType = TYLError.ErrorType.ErrorParameter;
//            ResponseJsonUtils.json(response, error);
//            return;
//        }

//        String pwd = jsonObject.getString("phone");

//        String email = request.getParameter("email");
//        UserInfo userInfo = new UserInfo(name, pwd, email);

        String phone = "123";
        String pwd = "123";

        DBManageUserOperation dbManageUserOperation = new DBManageUserOperation();
        String registStatus = new String();
        try {
            Boolean addResult = dbManageUserOperation.addUser(phone, pwd);
            if (addResult) {
                registStatus = "注册成功";
            } else  {
                registStatus = "注册失败";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter pw = response.getWriter();
        String docType = "<!DOCTYPE html> \n";
        pw.println(docType +
                "<html>\n" +
                "<head><title>" + "注册" + "</title></head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n" +
                "<h1 align=\"center\">" + registStatus + "</h1>\n" +
                "</ul>\n" +
                "</body></html>");
    }
}
