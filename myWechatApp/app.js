//app.js
var LoadData = require('./utils/loadData.js');

App({
  onLaunch: function () {
    var userName = wx.getStorageSync("USER_INFO_LOGIN_NAME");
    var userPwd = wx.getStorageSync("USER_INFO_USER_PWD");
    var userType = wx.getStorageSync("userType");
    if (userName.length > 0 && userPwd.length > 0) {
      if (userType == 2) {  // user
        wx.redirectTo({
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
    } 
    // 展示本地存储能力
    var logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)

    // 登录
    wx.login({
      success: res => {
        wx.setStorageSync("payCode", res.code)
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
  },  
})