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
    userPwd: 'taoyali',
  },

  loginAction: function(e) {
    debugger
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

          var pageUrl = '../dealerPages/dealerOrderList';

          if (responseData["userType"] == 1) {
            pageUrl = '../order/adminOrder';
          } 
          wx.navigateTo({
            url: pageUrl
          })
        }
      });      
    }

    // wx.navigateTo({
    //   url: '../dealerPages/dealerOrderList'
    // })



    // wx.navigateTo({

    //   url: '../order/adminOrder', 

    //   // url: '../order/orderDetail?name=' + 'good'.replace(/&/g, ''), 

    //   // url: '../order/adminOrder',
    //   // if (this.data.phoneNumberPlaceHolder) {
    //   //   url: '../order/orderDetail?name=' + this.data.phoneNumberPlaceHolder,
    //   // } else {
    //   //   url: '../order/orderDetail?name=' + 'error',
    //   // }      
    // })
  },

  getLoginData: function (parameters, callBack) {
    debugger
    LoadData.requestData('POST', 'https://net.rojo.vip:8443/rojo/LoginServlet', parameters, callBack);
  },
})