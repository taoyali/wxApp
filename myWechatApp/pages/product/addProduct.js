var LoadData = require('../../utils/loadData.js');

Page({

  /**
   * 页面的初始数据
   */
  data: {
    productName: "",
    price: 0.0,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    
  },

  addProduct: function (e) {
    debugger
    var parameter = {
      "productName": e.detail.value["productName"],
      "price": e.detail.value["price"],
    };

    LoadData.requestData('POST', 'https://net.rojo.vip:8443/rojo/add.ProductServlet', parameter, function (data) {
      debugger
      console.log(data)
    });
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
    
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
    
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
    
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
    
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
    
  }
})