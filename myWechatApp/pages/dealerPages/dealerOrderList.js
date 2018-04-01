var LoadData = require('../../utils/loadData.js');
var WXPay = require('../../utils/wxPay.js');

Page({
  /**
   * 页面的初始数据
   */
  data: {
    dealerOrders: [
      // {
      //   // 经销商名称：
      //   dealerCode: "经销商1 code",
      //   dealerName: "杭州天鑫五金建材有限公司",
      //   customName: "客户名称",
      //   customPhoneNumber: "13645527410",
      //   productName: "H103锁",
      //   count: '5',
      //   totalPrice: '1000',
      //   // 安装时间
      //   installTime: "time",
      //   // 下单时间
      //   orderTime: "time",
      //   doorType: '门类型',
      //   // 衬板宽、高
      //   scaleboardWidth: "50",
      //   scaleboardHeight: "50",
      //   address: "顾客地址",
      //   // 备注：
      //   remake: "及时送货， 安装",
      //   status: 2,
      // }
    ],
  },

  dealerOrderDetail: function(e) {
    var index = e.currentTarget.dataset.index;
    var item = this.data.dealerOrders[index];
    // var modelString = JSON.stringify(item);
    try {
      wx.setStorageSync('orderDetail', item);
      wx.navigateTo({
        url: '../order/orderDetail',
      })
    } catch (e) {

    }
  },

  getHttpData: function () {
    var that = this;
    var phone = wx.getStorageSync("USER_INFO_LOGIN_NAME");
    var pwd = wx.getStorageSync("USER_INFO_USER_PWD");
    var parameters = {
      'phone': phone,
      'pwd': pwd,
      'pageIndex': 1,
      'pageSize': 100,
    }
    LoadData.requestData('POST', 'https://net.rojo.vip:8443/rojo/query.OrderServlet', parameters, function (response) {
      that.setData({ dealerOrders: response });
    });
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    wx.showLoading({
      title: '努力加载中...',
    })
  },

  payAction: function (e) {
    var code = wx.getStorageSync("payCode")
    var index = e.currentTarget.dataset.index;
    var item = this.data.dealerOrders[index];
    var money = item.count * item.totalPrice;
    var id = item.id;
    debugger
    WXPay.requestPay(id, money, code, function () {
      LoadData.requestData('POST', 'https://net.rojo.vip:8443/rojo/paySucess.WXPayNotifyServlet', { id: id }, function (response) {
        debugger
        this.getHttpData();
      })
    })
  },

  addOrder: function() {
    wx.navigateTo({
      url: "../dealerPages/dealerAddOrder",
    })
  },
  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
    listCompment.refresh(); //下拉刷新
  },

  // /**
  //  * 页面上拉触底事件的处理函数
  //  */
  // onReachBottom: function () {
  //   listCompment.next(); //加载下一页
  // },

  onShow: function () {
    console.log('App Show');
    var that = this;
    this.getHttpData();
  },

  upper: function (e) {
    console.log(e)
  },
  lower: function (e) {
    console.log(e)
  },
  scroll: function (e) {
    console.log(e)
  },
  scrollToTop: function (e) {
    this.setAction({
      scrollTop: 0
    })
  },
  taptosenddata: function (e) {
    this.setData({
      xxx: "ddddd"
    })
  },
  tap: function (e) {

  },
  tapMove: function (e) {
    this.setData({
      scrollTop: this.data.scrollTop + 10
    })
  },

})