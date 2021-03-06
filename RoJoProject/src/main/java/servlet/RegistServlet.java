package servlet;

import com.alibaba.fastjson.JSONObject;
import util.*;
import DB.ManageUserOperation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.sym.error;
import static util.NetStatus.NetStatusType.NetStatusTypeError;

/**
 * Created by taoyali on 2017/9/3.
 */
@WebServlet(name = "RegistServlet")
public class RegistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        String json = FromJson.getRequestJsonString(request);
//        ManageUser manageUser = FromJson.StringToEntity(json, ManageUser.class);//json字符串转换成jsonobject对象

        try {
            JSONObject jsonObject = FromJson.getJsonObject(request);
            String name = jsonObject.getString("name");
            String pwd = jsonObject.getString("pwd");
            String phone = jsonObject.getString("pwd");
            if (!CheckPhone.isPhoneLegal(phone)) {

                TYLError error = new TYLError();
                error.errorCode = "1234";
                error.errorInfo = "参数错误";
                error.errorType = TYLError.ErrorType.ErrorParameter;
                NetStatus status = new NetStatus();
                status.error = error;
                status.status = NetStatusTypeError;
                ResponseJsonUtils.json(response, status);
                return;
            }

            ManageUserOperation dbManageUserOperation = new ManageUserOperation();
            String registStatus = new String();
            Boolean status = false;
            try {
                status = dbManageUserOperation.addManager(phone, pwd);
                if (status) {
                    registStatus = "注册成功";
                } else  {
                    registStatus = "注册失败";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (status) {
                ResponseJsonUtils.json(response, error);
            }

        } catch (Exception e) {

        }
    }
}
