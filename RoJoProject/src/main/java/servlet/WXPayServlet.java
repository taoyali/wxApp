package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.WxPayConfig;
import com.alibaba.fastjson.JSONObject;
import org.codehaus.jackson.map.ObjectMapper;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import util.*;
import entity.PayInfo;

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

    private void wxPay(HttpServletRequest request, HttpServletResponse response) throws Exception {

        JSONObject jsonObject = FromJson.getJsonObject(request);
        String code =  jsonObject.getString("code");
        String id = jsonObject.getString("id");
        String clintIP = WXPayUtil.getClientIp(request);
        int money = jsonObject.getInteger("money") * 100;

        String resultStr = getOpenId(code);
        jsonObject = JSONObject.parseObject(resultStr);
        String openID = jsonObject.getString("openid");
        openID = openID.replace("\"", "").trim();

        if (openID != null && openID.length() > 0) {
            Map<String, String> result = prePay(openID, clintIP, money, id);
            ResponseJsonUtils.json(response, result);
        } else  {
            ResponseJsonUtils.json(response, "");
        }
    }

    private String getOpenId(String code) {
        try {
            HttpRequest httpResult = new HttpRequest();
            String url = WxPayConfig.openIdURL + "?appid=" + WxPayConfig.appid +
                    "&secret=" + WxPayConfig.appSecret + "&js_code=" + code + "&grant_type=authorization_code";
            String resultStr = httpResult.sendGet(url);
            return resultStr;

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public Map<String, String> prePay(String openId, String clientIP, int money, String attach) {

        String content = null;
        Map map = new HashMap();

        boolean result = true;
        String info = "";

        if(openId.length() < 0) {
            result = false;
            info = "获取到openId为空";
        } else {

            String randomNonceStr = RandomUtils.generateMixString(32);
            return unifiedOrder(openId, clientIP, money, attach, randomNonceStr);
        }

        try {
            map.put("info", info);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }


    /**
     * 调用统一下单接口
     * @param openId
     */
    private Map<String, String> unifiedOrder(String openId, String clientIP, int money, String attach, String randomNonceStr) {

        try {

            String url = WxPayConfig.pay_url;
            Date date = new Date();
            String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
            PayInfo payInfo = WxPayConfig.wxPayConfig(openId, clientIP, money, attach, randomNonceStr);

            JAXBContext context = JAXBContext.newInstance(PayInfo.class);    // 获取上下文对象
            Marshaller marshaller = context.createMarshaller(); // 根据上下文获取marshaller对象
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");  // 设置编码字符集
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); // 格式化XML输出，有分行和缩进
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            marshaller.marshal(payInfo, baos);
            String xml = new String(baos.toByteArray());         // 生成XML字符串

            xml = xml.replace("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>", "");
            StringBuffer buffer = HttpRequest.httpsRequest(url, "POST", xml);

            Map <String, String> map = new HashMap<String, String>();
            Map<String, Object> result = xmlUtil.Dom2Map(buffer.toString());

            String packageString = "prepay_id=" + result.get("prepay_id").toString();
            String paySign = payInfo.paySign(WxPayConfig.key, result.get("nonce_str").toString(), packageString, String.valueOf(timestamp));

            map.put("nonceStr", result.get("nonce_str").toString());
            map.put("package", packageString);
            map.put("paySign", paySign);
            map.put("timestamp", String.valueOf(timestamp));

            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new HashMap<String, String>();
    }
}
