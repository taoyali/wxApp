// var LoadUtil = require('../../utils/loadData.js');
// var loadUtil = new LoadUtil();

// var util = require('../../utils/util.js');
// var ListCompment = require('../../lib/ListCompment.js');
// var listCompment = new ListCompment({
//   name: 'adminOrders',
//   api: '/partner-userMforlist',
//   ajax: util.ajax //这是我自己封装的ajax
// });

Page({
  /**
   * 页面的初始数据
   */
  data: {
    adminOrders: [
    //   {
    //   // 经销商名称：
    //   distributorCode: "经销商1 code",
    //   distributorCompanyName: "经销商1 公司",
    //   distributorName: "经销商1",
    //   distributorPhoneNumber: "13645527410",
    //   productName: "产品1",
    //   productCount: '5',
    //   productTotalPrice: '1000',
    //   // 安装时间
    //   installTime: "time",
    //   // 下单时间
    //   orderTime: "time",
    //   doorType: '门类型',
    //   // 衬板宽、高
    //   LinerWidth: "50",
    //   LinerHeight: "50",
    //   customerAddress: "顾客地址",
    //   // 备注：
    //   remarks: "及时送货， 安装",
    //   status: 2,
    // },
    ],
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
    this.getHttpData("swiperData", "json");
  },

  getHttpData: function (key, _type) {
    var that = this;
    this.loadScrollImages(key, _type, function (data) {
      wx.hideLoading();
      console.log(data);
      that.setData({ swiperData: data });
    });
  },

  // 从 wxappclub 的 api 中心获取数据的方法
  // key 表示数据名称，_type 数据类型，callback 表示请求成功的回调函数
  // 回调函数的的参数，用于携带请求得到的数据
  loadScrollImages: function (key, _type, callback) {
    wx.request({
      url: 'https://api.wxappclub.com/get',
      data: {
        // 笔者的API中心，提供给各位使用
        "appkey": "eaa7gcwem04j8sak7hm8g88mkftgb26h",
        "key": key,
        "type": _type
      },
      method: 'GET',
      header: {
        'content-type': 'application/json'
      },
      success: function (res) {
        if (res.data.success) {
          // console.log(res.data.result.value);
          callback(res.data.result.value);
        }
      }
    });
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

  loginAction: function () {
    if (true) {
      console.log("判断登录");
    }
    wx.navigateTo({
      url: '../order/adminOrderDetail?name=' + 'good'.replace(/&/g, ''),     
    })
  }
})