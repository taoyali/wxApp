//app.js
var LoadData = require('./utils/loadData.js');

App({
  onLaunch: function () {
    var userName = wx.getStorageSync("USER_INFO_LOGIN_NAME");
    var userPwd = wx.getStorageSync("USER_INFO_USER_PWD");
    var userType = wx.getStorageSync("userType");
    if (userName.length > 0 && userPwd.length > 0) {
      if (userType == 2) {  // user
        wx.navigateTo({
          url: 'pages/dealerPages/dealerOrderList'
        })
      } else if (userType == 1) {
        wx.switchTab({
          url: "pages/order/adminOrder",
        })
      } else {
        wx.navigateTo({
          url: "pages/login/login",
        })
      }
    } else {
      wx.navigateTo({
        url: "pages/login/login",
      })
    }
    // 展示本地存储能力
    var logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)

    // 登录
    wx.login({
      success: res => {

        // // 发送 res.code 到后台换取 openId, sessionKey, unionId
        // var service_url = 'https://net.rojo.vip:8443/rojo/getOpenId.WXPayServlet';//需要将服务器域名添加到小程序的request合法域名中，而且必须是https开头    
        // var parameeters = {
        //   code: res.code
        // }
        // LoadData.requestData('POST', service_url, parameters, function () {
        //     if (res.data != null && res.data != undefined && res.data != '') {
        //       wx.setStorageSync("openid", res.data.openid);//将获取的openid存到缓存中    
        //     }
        // });

        debugger

        var service_url = 'https://net.rojo.vip:8443/rojo/getOpenId.WXPayServlet';//需要将服务器域名添加到小程序的request合法域名中，而且必须是https开头    
        wx.request({
          url: service_url,
          data: { code: res.code},
          method: 'POST',
          success: function (res) {
            debugger
            console.log(res);
            if (res.data != null && res.data != undefined && res.data != '') {
              wx.setStorageSync("openid", res.data.openid);//将获取的openid存到缓存中    
            }
          }
        });
      }
    }),

    // 获取用户信息
    wx.getSetting({
      success: res => {
        if (res.authSetting['scope.userInfo']) {
          // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
          wx.getUserInfo({
            success: res => {
              // 可以将 res 发送给后台解码出 unionId
              this.globalData.userInfo = res.userInfo

              // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
              // 所以此处加入 callback 以防止这种情况
              if (this.userInfoReadyCallback) {
                this.userInfoReadyCallback(res)
              }
            }
          })
        }
      }
    })
  },

  globalData: {
    userInfo: null,
  }  
})