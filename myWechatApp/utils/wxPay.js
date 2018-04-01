
function requestPay(id, money, code, callback) {

  var service_url = 'https://net.rojo.vip:8443/rojo/wxPay.WXPayServlet';//需要将服务器域名添加到小程序的request合法域名中，而且必须是https开头    

  // service_url = 'https://127.0.0.1:8443/wxPay.WXPayServlet';

  wx.request({
    url: service_url,
    data: { id: id, money: money, code: code },
    method: 'POST',
    success: function (res) {
      debugger
      console.log(res);
      if (res.data != null && res.data != undefined && res.data != '') {
        wx.setStorageSync("openid", res.data.openid);//将获取的openid存到缓存中  
        debugger
        var resultData = res.data
        var timeStamp = resultData.timestamp

        wx.requestPayment({
          timeStamp: resultData.timestamp,
          nonceStr: resultData.nonceStr,
          package: resultData.package,
          signType: 'MD5',
          paySign: resultData.paySign,
          success: function (res) {
            wx.showToast({
              title: '支付成功',
              icon: 'success',
              duration: 2000
            })
            callback();
          },
          fail: function (res) {
            wx.showToast({
              title: '支付失败',
              icon: 'fail',
              duration: 2000
            })
            // callback();
          }
      })
    }
  }
})
}

module.exports = {
  requestPay: requestPay
}