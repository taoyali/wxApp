var LoadData = require('../../utils/loadData.js');

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
      //   productTotalPrice: '1000',
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

  dealerOederDetail: function(e) {
    var index = e.currentTarget.dataset.index;
    var item = this.data.dealerOrders[index];
    var modelString = JSON.stringify(item);
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
    debugger
    LoadData.requestData('POST', 'https://net.rojo.vip:8443/rojo/query.OrderServlet', parameters, function (response) {
      debugger
      that.setData({ dealerOrders: response });
    });
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    // listCompment.setParams('cate_id', 1).setParams('page_size', 10); //设置额外传参
    // listCompment.init(this); //初始化，传入this
    wx.showLoading({
      title: '努力加载中...',
    })
    // 页面初始化时，请求服务器，获取 swiperData ，用于渲染轮播图
    // 为了避免 this 指向错误，截取this，赋值给 that
    var that = this;
    this.getHttpData();
  },

  // 从 wxappclub 的 api 中心获取数据的方法
  // key 表示数据名称，_type 数据类型，callback 表示请求成功的回调函数
  // 回调函数的的参数，用于携带请求得到的数据
  loadScrollImages: function (key, _type, callback) {

  },

  addOrder: function() {
    wx.navigateTo({
      url: "../dealerPages/dealerAddOrder",
    })
  },
  // /**
  //  * 页面相关事件处理函数--监听用户下拉动作
  //  */
  // onPullDownRefresh: function () {
  //   listCompment.refresh(); //下拉刷新
  // },

  // /**
  //  * 页面上拉触底事件的处理函数
  //  */
  // onReachBottom: function () {
  //   listCompment.next(); //加载下一页
  // },

  onShow: function () {
    console.log('App Show')

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