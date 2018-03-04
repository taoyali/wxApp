// pages/login/login.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    phoneNumberPlaceHolder: '手机号',
    passWordPlaceHolder: '密码',
    loginButtonText: '登录',
    userIphone: '13645525753',
    userPwd: '123',
  },

  loginAction: function() {
    if (true) {
      console.log("判断登录");
    }

    wx.switchTab({
      url: '../order/adminOrder'
    })
    // wx.navigateTo({

    //   url: '../order/adminOrder', 

    //   // url: '../order/adminOrderDetail?name=' + 'good'.replace(/&/g, ''), 

    //   // url: '../order/adminOrder',
    //   // if (this.data.phoneNumberPlaceHolder) {
    //   //   url: '../order/adminOrderDetail?name=' + this.data.phoneNumberPlaceHolder,
    //   // } else {
    //   //   url: '../order/adminOrderDetail?name=' + 'error',
    //   // }      
    // })
  },
})