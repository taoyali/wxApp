package util;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import com.alibaba.fastjson.*;

/**
 * Created by taoyali on 2018/1/28.
 */
public class FromJson {

    /**
     * 解析前台获得的交互数据
     * @param request
     * @return
     * @throws IOException
     */
    public static JSONObject getJsonObject(HttpServletRequest request) throws IOException{
        String resultStr = "";
        String readLine;
        StringBuffer sb = new StringBuffer();
        BufferedReader responseReader = null;
        OutputStream outputStream = null;
        try {
            responseReader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
            while ((readLine = responseReader.readLine()) != null) {
                sb.append(readLine).append("\n");
            }
            responseReader.close();
            resultStr = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }
        JSONObject jsonObject = JSONObject.parseObject(resultStr);
        return jsonObject;
    }

    /***
     * 获取 request 中 json 字符串的内容
     *
     * @param request
     * @return : <code>byte[]</code>
     * @throws IOException
     */
    public static String getRequestJsonString(HttpServletRequest request)
            throws IOException {
        String submitMehtod = request.getMethod();
        // GET
        if (submitMehtod.equals("GET")) {
            String parameter = request.getQueryString();
            return new String(parameter.getBytes("iso-8859-1"),"utf-8").replaceAll("%22", "\"");
            // POST
        } else {
            return getRequestPostStr(request);
        }
    }

    /**
     * 描述:获取 post 请求的 byte[] 数组
     * <pre>
     * 举例：
     * </pre>
     * @param request
     * @return
     * @throws IOException
     */
    public static byte[] getRequestPostBytes(HttpServletRequest request)
            throws IOException {
        int contentLength = request.getContentLength();
        if(contentLength<0){
            return null;
        }
        byte buffer[] = new byte[contentLength];
        for (int i = 0; i < contentLength;) {

            int readlen = request.getInputStream().read(buffer, i,
                    contentLength - i);
            if (readlen == -1) {
                break;
            }
            i += readlen;
        }
        return buffer;
    }

    /**
     * 描述:获取 post 请求内容
     * <pre>
     * 举例：
     * </pre>
     * @param request
     * @return
     * @throws IOException
     */
    public static String getRequestPostStr(HttpServletRequest request)
            throws IOException {
        byte buffer[] = getRequestPostBytes(request);
        String charEncoding = request.getCharacterEncoding();
        if (charEncoding == null) {
            charEncoding = "UTF-8";
        }
        return new String(buffer, charEncoding);
    }


    /**
     * 字符串转换为实体类
     *
     */
    public static <T> T StringToEntity(String str, Class<T> classT) {
        T t = JSON.parseObject(str, classT);
        return t;
    }

    /**
     * 字符串转换为实体集合
     *
     */
    public static <T> ArrayList<T> StringToEntityList(String str, Class<T> classT) {
        List<T> lst = JSON.parseArray(str, classT);
        return (ArrayList<T>) lst;
    }

    /**
     * 实体类转换为字符串
     *
     */
    public static <T> String EntityToString(T data) {
        String str = JSON.toJSONString(data);
        return str;
    }
}
