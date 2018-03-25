package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Dictionary;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import DAO.Dealer;
import DAO.ManageUser;
import DB.DealerOperation;
import DB.ManageUserOperation;
import com.alibaba.fastjson.JSONObject;
import util.*;

import javax.servlet.http.Cookie;


/**
 * Created by taoyali on 2017/8/27.
 */
public class LoginServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter pw = response.getWriter();
//        pw.write("<h1> hello servlet </h1>");
//        String title = "使用 GET 方法读取表单数据";
//        String phone = new String(request.getParameter("name").getBytes("ISO8859-1"),"UTF-8");
//        String password = new String(request.getParameter("password").getBytes("ISO8859-1"), "UTF-8");


        JSONObject jsonObject = null;
        try {
            jsonObject = FromJson.getJsonObject(request);
            String userName = jsonObject.getString("userName");
            String userPwd = jsonObject.getString("userPwd");


            int loginStatus = 1;
            int userType = 1; // 管理员
            String loginUserInfoString = new String();
            try {
                ManageUserOperation dbuse = new ManageUserOperation();
                List<ManageUser> userInfos = dbuse.selectManager(userName, userPwd, new ManageUser());
                if (userInfos.size() > 0) {
                    ManageUser manageUser = userInfos.get(0);

                    //登陆成功
                    //设置session
                    request.getSession().setAttribute("USER_INFO_LOGIN_NAME", manageUser.getPhone());
                    request.getSession().setAttribute("USER_INFO_USER_ID", String.valueOf(manageUser.getId()));
                    request.getSession().setAttribute("USER_INFO_USER_PWD", manageUser.getPwd());
                    //设置cookie
                    response.addCookie(new Cookie("USER_INFO_LOGIN_NAME", manageUser.getName()));
                    response.addCookie(new Cookie("USER_INFO_USER_ID", String.valueOf(manageUser.getId())));
                    response.addCookie(new Cookie("USER_INFO_USER_PWD", manageUser.getPwd()));
                    loginStatus = 0;
                    userType = 1; // 用户
                } else {
                    DealerOperation dealerOperation = new DealerOperation();
                    List<Dealer> dealers = dealerOperation.query(userName, userPwd, new Dealer());
                    if (dealers.size() > 0) {
                        Dealer dealer = dealers.get(0);
                        //登陆成功
                        //设置session
                        request.getSession().setAttribute("USER_INFO_LOGIN_NAME", dealer.phone);
                        request.getSession().setAttribute("USER_INFO_USER_ID", String.valueOf(dealer.id));
                        request.getSession().setAttribute("USER_INFO_USER_PWD", dealer.pwd);
                        //设置cookie
                        response.addCookie(new Cookie("USER_INFO_LOGIN_NAME", dealer.phone));
                        response.addCookie(new Cookie("USER_INFO_USER_ID", String.valueOf(dealer.id)));
                        response.addCookie(new Cookie("USER_INFO_USER_PWD", dealer.pwd));

                        loginStatus = 0;
                        userType = 2; // 用户
                    }
                }
                if (loginStatus == 0) {
                    LinkedHashMap<String, Object> data = new LinkedHashMap<String, Object>();
                    data.put("loginStatus", loginStatus);
                    data.put("authonKey", "authonKey");
                    data.put("authonToken", "authonToken");
                    data.put("userType", userType);
                    ResponseJsonUtils.json(response, data);
                }
            } catch (Exception e) {
                e.printStackTrace();
                loginStatus = 1;
            }
        } catch (Exception e) {
            System.out.print(e);
            e.printStackTrace();
        }
    }
}
