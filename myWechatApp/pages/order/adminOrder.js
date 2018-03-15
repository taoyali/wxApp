var LoadData = require('../../utils/loadData.js');

// var util = require('../../utils/util.js');
// var ListCompment = require('../../utils/ListCompment.js');
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
      // {
      //   // 经销商名称：
      //   id:"111",
      //   dealerCode: "经销商1 code",
      //   dealerName: "杭州天鑫五金建材有限公司",
      //   customName: "客户名称",
      //   phone: "13645527410",
      //   productName: "H103锁",
      //   count: '5',
      //   totalPrice: '1000',
      //   // 安装时间
      //   installDate: "time",
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
      // },
    ]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    // listCompment.setParams('pageIndex', 1).setParams('pageSise', 100); //设置额外传参
    // listCompment.init(this); //初始化，传入this
    wx.showLoading({
      title: '努力加载中...',
    })
    // 页面初始化时，请求服务器，获取 swiperData ，用于渲染轮播图
    // 为了避免 this 指向错误，截取this，赋值给 that
    var that = this;
    this.getHttpData();
  },

  getHttpData: function (key) {
    var that = this;
    var data = {
      'username': '123',
      'userpwd': '123',
      'pageIndex': 10,
      'pageSise': 100,
    }
    LoadData.requestData('POST', 'https://127.0.0.1:8433/query.OrderServlet', data, function (data) {
      wx.hideLoading();
      console.log(data);
      that.setData({ adminOrders: data });
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

  orderDetail: function (e) {
    var index = e.currentTarget.dataset.index;
    var item = this.data.adminOrders[index];
    var modelString = JSON.stringify(item);
    try {
      wx.setStorageSync('orderDetail', item);
      wx.navigateTo({
        url: '../order/orderDetail',
      })
    } catch (e) {

    }
  },

  // 技术文章：http://p.codekk.com/detail/Android/hss01248/wxListview
  onShow: function () {

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
    // for (var i = 0; i < order.length; ++i) {
    //   if (order[i] === this.data.toView) {
    //     this.setData({
    //       toView: order[i + 1],
    //       scrollTop: (i + 1) * 200
    //     })
    //     break
    //   }
    // }
  },
  tapMove: function (e) {
    this.setData({
      scrollTop: this.data.scrollTop + 10
    })
  },

})