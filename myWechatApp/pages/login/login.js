// pages/login/login.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    phoneNumberPlaceHolder: '手机号',
    passWordPlaceHolder: '密码',
    loginButtonText: '登录',
  },
  loginAction: function() {
    if (true) {
      console.log("判断登录");
    }
    wx.navigateTo({
      url: '../order/adminOrder',
    })
  }
})