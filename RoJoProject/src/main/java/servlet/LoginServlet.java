package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import DAO.ManageUser;
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
        String title = "使用 GET 方法读取表单数据";
//        String phone = new String(request.getParameter("name").getBytes("ISO8859-1"),"UTF-8");
//        String password = new String(request.getParameter("password").getBytes("ISO8859-1"), "UTF-8");


        JSONObject jsonObject = null;
        try {
            jsonObject = FromJson.getJsonObject(request);
            String userName = jsonObject.getString("userName");
            String userPwd = jsonObject.getString("userPwd");

            ManageUserOperation dbuse = new ManageUserOperation();

            String loginStatus = new String();
            String loginUserInfoString = new String();
            try {
                List<ManageUser> userInfos = dbuse.selectByName(userName, userPwd, new ManageUser());
                if (userInfos.size() > 0) {
                    ManageUser manageUser = userInfos.get(0);

                    //登陆成功
                    //设置session
                    request.getSession().setAttribute("USER_INFO_LOGIN_NAME", manageUser.getName());
                    request.getSession().setAttribute("USER_INFO_USER_ID", String.valueOf(manageUser.getId()));
                    request.getSession().setAttribute("USER_INFO_USER_PWD", manageUser.getPwd());
                    //设置cookie
                    response.addCookie(new Cookie("USER_INFO_LOGIN_NAME", manageUser.getName()));
                    response.addCookie(new Cookie("USER_INFO_USER_ID", String.valueOf(manageUser.getId())));
                    response.addCookie(new Cookie("USER_INFO_USER_PWD", manageUser.getPwd()));
                    loginUserInfoString = " <li><b> userId</b>: "
                            + manageUser.getId()  + "\n" +
                            " <li><b> name</b>: "
                            + manageUser.getName() + "\n" +
                            " <li><b> phone</b>: "
                            +  manageUser.getPhone() + "\n" +
                            " <li><b> pwd</b>: "
                            +  manageUser.getPwd() + "\n";
                    loginStatus = "登录成功";
                } else {
                    loginStatus = "用户不存在或密码错误";
                }
                String docType = "<!DOCTYPE html> \n";
                pw.println(docType +
                        "<html>\n" +
                        "<head><title>" + title + "</title></head>\n" +
                        "<body bgcolor=\"#f0f0f0\">\n" +
                        "<h1 align=\"center\">" + title + "</h1>\n" +
                        "<ul>\n" +
                        "  <li><b>站点名</b>："
                        + userName + "\n" +
                        "  <li><b>网址</b>："
                        + request.getParameter("password") + "\n" +
                        " <li><b> 登录状态</b>: "
                        + loginStatus + "\n"
                        + loginUserInfoString +
                        "</ul>\n" +
                        "</body></html>");
                ResponseJsonUtils.json(response, "");
            } catch (Exception e) {
                e.printStackTrace();
                loginStatus = "服务器异常";
            }
        } catch (Exception e) {
            System.out.print(e);
            e.printStackTrace();
        }
    }
}
