package DAO;

/**
 * 小程序微信支付的配置文件
 * @author
 *
 */
public class WxPayConfig {
    //小程序appid
    public static final String appid = "wx2a7b936d4cc40883";
    //小程序 secret
    public static final String appSecret = "123456";
    //微信支付的商户id
    public static final String mch_id = "";
    //微信支付的商户密钥
    public static final String key = "";
    //支付成功后的服务器回调url
    public static final String notify_url = "https://??/??/weixin/api/wxNotify";
    //签名方式，固定值
    public static final String SIGNTYPE = "MD5";
    //交易类型，小程序支付的固定值为JSAPI
    public static final String TRADETYPE = "JSAPI";
    //微信统一下单接口地址
    public static final String pay_url = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    public static final String OpenIdURL = "https://api.weixin.qq.com/sns/jscode2session";
}
