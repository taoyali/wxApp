package entity;

import util.WXPayUtil;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by Hyman on 2017/2/28.
 */

@XmlRootElement(name="xml")
public class PayInfo implements Serializable {

    //小程序appid
    public String appid = "wx2a7b936d4cc40883";
    //微信支付的商户id
    public String mch_id = "1499340202";
    //支付成功后的服务器回调url
    public String notify_url = "http://www.weixin.qq.com/wxpay/pay.php";
    //签名类型
    public String sign_type = "MD5";
    public String body = "SSH";
    //交易类型,JSAPI
    public String trade_type = "JSAPI";

    public String nonce_str;
    public String sign;

    public String out_trade_no;
    public int total_fee;
    public String spbill_create_ip;
    public String openid;
    // 订单信息， 支付通知中原样返回
    public  String attach;

    public void configSign(String key) throws Exception {
        String sb = "appid=" + appid
                + "&attach=" + attach
                + "&body=" + body
                + "&mch_id=" + mch_id
                + "&nonce_str=" + nonce_str
                + "&notify_url=" + notify_url
                + "&openid=" + openid
                + "&out_trade_no=" + out_trade_no
                + "&sign_type=" + sign_type
                + "&spbill_create_ip=" + spbill_create_ip
                + "&total_fee=" + total_fee
                + "&trade_type=" + trade_type
                + "&key=" + key;

        sign = WXPayUtil.getMD5(sb.trim()).toUpperCase();
    }

    /**
     *
     * @param key
     * @param nonceStr
     * @param packageString
     * @param timestamp
     * @return
     * @throws Exception
     */
    public String paySign(String key, String nonceStr, String packageString, String timestamp) throws Exception {
        String sb = "appId=" + appid
                + "&nonceStr=" + nonceStr
                + "&package=" + packageString
                + "&signType=" + "MD5"
                + "&timeStamp=" + timestamp
                + "&key=" + key;
        return WXPayUtil.getMD5(sb.trim()).toUpperCase();
    }
}
