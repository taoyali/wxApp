package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import DAO.WxPayConfig;
import com.alibaba.fastjson.JSONObject;
import util.FromJson;
import util.ResponseJsonUtils;
import util.HttpRequest;

//import com.google.gson.JsonParser;
//import com.google.gson.JsonObject;
//import org.apache.log4j.Logger;

/**
 * Created by taoyali on 2018/3/26.
 */
public class WXPayServlet extends HttpServlet {

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

    private String getOpenId(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpRequest httpResult = new HttpRequest();
            JSONObject jsonObject = FromJson.getJsonObject(request);
            String code =  jsonObject.getString("code");
            String url = WxPayConfig.OpenIdURL + "?appid=" + WxPayConfig.appid +
                    "&secret=" + WxPayConfig.appSecret + "&js_code=" + code + "&grant_type=authorization_code";
            String str = httpResult.sendGet(url);
            ResponseJsonUtils.json(response, str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


}
