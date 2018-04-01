package entity;

import util.TimeUtils;
import util.WXPayUtil;

import java.util.Date;

/**
 * 小程序微信支付的配置文件
 * @author
 *
 */
public class WxPayConfig {

    //小程序appid
    public static final String appid = "wx2a7b936d4cc40883";
    //微信支付的商户id
    public static final String mch_id = "1499340202";
    //小程序 secret
    public static final String appSecret = "9b79778bb9408120fbf1875e1b18042a";
    //微信支付的商户密钥
    public static final String key = "y9U3e4F7Dm9y5vy3tWd2X6Cr4jnb7m3Q";
    //支付成功后的服务器回调url
    public static final String notify_url = "https://net.rojo.vip:8443/rojo/payNotify.WXPayNotifyServlet";


    //微信统一下单接口地址
    public static final String pay_url = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    public static final String openIdURL = "https://api.weixin.qq.com/sns/jscode2session";

    public static final String TIME_FORMAT = "yyyyMMddHHmmss";

    public static final int TIME_EXPIRE = 2;  //单位是day

    public static PayInfo wxPayConfig(String openId, String clientIP, int money, String attach, String randomNonceStr) throws Exception {

        Date date = new Date();
        String timeStart = TimeUtils.getFormatTime(date, TIME_FORMAT);
        String timeExpire = TimeUtils.getFormatTime(TimeUtils.addDay(date, TIME_EXPIRE), TIME_FORMAT);

        String randomOrderId = WXPayUtil.getRandomOrderId();

        PayInfo payInfo = new PayInfo();
        payInfo.nonce_str = randomNonceStr;
        payInfo.out_trade_no = randomOrderId;
        payInfo.total_fee = money;
        payInfo.spbill_create_ip = clientIP;
        payInfo.attach = attach;
        payInfo.openid = openId;
        payInfo.configSign(key);
        return payInfo;
    }
}
