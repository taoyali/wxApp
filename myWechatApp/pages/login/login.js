var LoadData = require('../../utils/loadData.js');

Page({

  /**
   * 页面的初始数据
   */
  data: {
    phoneNumberPlaceHolder: '手机号',
    passWordPlaceHolder: '密码',
    loginButtonText: '登录',
    // userName: '',
    // userPwd: '',
    userName: '18516556064',
    // userPwd: '123',
    userPwd: 'taoyali',
  },

  loginAction: function(e) {
    wx.showLoading({
      title: '登录中...',
    })
    var userName = e.detail.value["userName"];
    var userPwd = e.detail.value["userPwd"];
    var parameters = {
      "userName": userName,
      "userPwd": userPwd
    }
    if (userName.length == 11 && userPwd.length > 0) {
      this.getLoginData(parameters, function (responseData) {
        if (responseData["loginStatus"] == 0) {
          wx.setStorageSync("USER_INFO_LOGIN_NAME", userName);
          wx.setStorageSync("USER_INFO_USER_PWD", userPwd);
          wx.setStorageSync("userType", responseData["userType"]);
          wx.setStorageSync("userId", responseData['userId']);

          if (responseData["userType"] == 2) {
            wx.redirectTo({
              url: '../dealerPages/dealerOrderList',
            })
          } else {
            wx.switchTab({
              url: '../order/adminOrder',
            })
          }
        }
      });      
    } else {
      wx.showToast({
        title: '请确认登录信息正确',
      })
    }
  },

  getLoginData: function (parameters, callBack) {
    LoadData.requestData('POST', 'https://net.rojo.vip:8443/rojo/LoginServlet', parameters, callBack);
  },
})